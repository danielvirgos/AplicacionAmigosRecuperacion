package com.example.aplicacionamigosrecuperacion.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UtilThread {

    private static final int NUMBER_OF_THREADS = Runtime.getRuntime().availableProcessors();

    public static final ExecutorService threadExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

}
