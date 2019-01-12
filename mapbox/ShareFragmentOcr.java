package com.example.vmann.mapbox;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;



public class ShareFragmentOcr extends ShareFragment {
    @Override
    protected String shareContent() {
        ArrayList<String> results = ((OCRResultActivity)getActivity()).getText();
        String description = ((OCRResultActivity)getActivity()).getLanguage();

        StringBuilder toShare = new StringBuilder(description);
        toShare.append("\n");
        for (String res : results) {
            toShare.append((WordUtils.capitalize(res)));
        }

        toShare.append("#GhioCa");

        return toShare.toString();
    }
}