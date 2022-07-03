package com.example.tjhdroid.utils;

import com.example.tjhdroid.tileprovider.modules.SqlTileWriter;

/**
 * Put there everything that could be done during a splash screen
 *
 * @author Fabrice Fontaine
 * @since 6.0.2
 */
public class DuringSplashScreen implements SplashScreenable {

    @Override
    public void runDuringSplashScreen() {
        final SqlTileWriter sqlTileWriter = new SqlTileWriter();
        sqlTileWriter.runDuringSplashScreen();
    }
}
