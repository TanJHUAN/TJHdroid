package com.example.tjhdroid.tileprovider.modules;

import android.util.Log;

import com.example.tjhdroid.api.IMapView;
import com.example.tjhdroid.tileprovider.IMapTileProviderCallback;
import com.example.tjhdroid.tileprovider.IRegisterReceiver;
import com.example.tjhdroid.tileprovider.MapTileProviderArray;
import com.example.tjhdroid.tileprovider.MapTileProviderBasic;
import com.example.tjhdroid.tileprovider.tilesource.FileBasedTileSource;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Causes Osmdroid to load from tiles from only the referenced file sources and
 * no where else. online sources are not even attempted.
 *
 * @since 5.0 Created by alex on 6/14/2015.
 */
public class OfflineTileProvider extends MapTileProviderArray implements IMapTileProviderCallback {

    private IArchiveFile[] archives;

    /**
     * Creates a {@link MapTileProviderBasic}.
     * throws with the source[] is null or empty
     */
    public OfflineTileProvider(final IRegisterReceiver pRegisterReceiver, File[] source) {
        super(FileBasedTileSource.getSource(source[0].getName()), pRegisterReceiver);
        List<IArchiveFile> files = new ArrayList<>();

        for (final File file : source) {
            final IArchiveFile temp = ArchiveFileFactory.getArchiveFile(file);
            if (temp != null)
                files.add(temp);
            else {
                Log.w(IMapView.LOGTAG, "Skipping " + file + ", no tile provider is registered to handle the file extension");
            }
        }
        archives = new IArchiveFile[files.size()];
        archives = files.toArray(archives);
        final MapTileFileArchiveProvider mapTileFileArchiveProvider = new MapTileFileArchiveProvider(pRegisterReceiver, getTileSource(), archives);
        mTileProviderList.add(mapTileFileArchiveProvider);

        final MapTileApproximater approximationProvider = new MapTileApproximater();
        mTileProviderList.add(approximationProvider);
        approximationProvider.addProvider(mapTileFileArchiveProvider);

    }

    public IArchiveFile[] getArchives() {
        return archives;
    }

    public void detach() {
        if (archives != null) {
            for (final IArchiveFile file : archives) {
                file.close();
            }
        }
        super.detach();
    }

    @Override
    protected boolean isDowngradedMode(final long pMapTileIndex) {
        return true;
    }
}
