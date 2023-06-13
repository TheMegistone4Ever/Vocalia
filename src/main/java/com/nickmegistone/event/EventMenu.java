package com.nickmegistone.event;

import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;

public interface EventMenu {

    public void selected(int index) throws IOException, JavaLayerException;
}
