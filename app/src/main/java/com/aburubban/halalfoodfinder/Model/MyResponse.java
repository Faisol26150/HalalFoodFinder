package com.aburubban.halalfoodfinder.Model;

import java.util.List;



/**
 * Created by Abu Rubban on 22-Sep-18.
 */

public class MyResponse {
    public long multicast_id;
    public int success;
    public int failure;
    public int canonical_ids;
    public List<Result> results;
}
