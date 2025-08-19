package com.pinnacle.dummyServlet.dao;

import java.util.List;

public record CleverTapResponse(String status, String processed, List<DataDetail> unProcessed) {

}
