package com.example.ideapad510.sherkat.controler;


public interface DataFlowControler {
    void getDataForTextViews(String txtView1, String txtView2, String txtView3);

    void setValidDataToTextViews();

    void getDataForDataBase(String tableItem1, String tableItem2, String tableItem3
            , String tableItem4, String tableItem5);

    void setValidDataToDataBase();

    boolean checkTextViewsInputValidation();

    boolean checkDataBaseInputValidation();
}
