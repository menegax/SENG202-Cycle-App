package seng202.team7;


import java.util.List;

/**
 * acts as the handler between java and javascript to pass onjects into google maps javascipt API
 * @author MorganEnglish
 */
public class JSHandler {

    private DatabaseRetriever databaseRetriever = new DatabaseRetriever();
    /**
     * Wrapper method for interacting with Javascript inside WebEngine
     * @return list of Wifi objects
     */
    public List<Wifi> getWifiJS(){

        return databaseRetriever.getWifiList();
    }

    public List<Wifi> getWifiJSFiltered(String burough, String type, String provider)
    {
        boolean buroughF = !(burough.equals("")|| burough.toLowerCase().equals("none")||burough==null);
        boolean typeF = !(type.equals("")||type.toLowerCase().equals("none")||type==null);
        boolean providerF = !(provider.equals("")||provider.toLowerCase().equals("none")||provider==null);

        if(!buroughF&&!typeF&&!providerF){
            //No fields selected
            return databaseRetriever.getWifiList();
        } else if(buroughF&&!typeF&&!providerF){
            //only burough selected
            return  databaseRetriever.queryWifi(StaticVariables.singleStringQuery(Wifi.tableName, Wifi.columns[1],burough));
        } else if(buroughF&&typeF&&!providerF){
            //only burough and type
            return databaseRetriever.queryWifi(StaticVariables.doubleStringQuery(Wifi.tableName, Wifi.columns[1],burough,Wifi.columns[2],type));
        } else if(buroughF&&typeF&&providerF){
            //all
            return databaseRetriever.queryWifi(StaticVariables.mapViewWifiQuery(burough,type,provider));
        } else if(!buroughF&&typeF&&!providerF) {
            //only type
            return  databaseRetriever.queryWifi(StaticVariables.singleStringQuery(Wifi.tableName, Wifi.columns[2],type));
        } else if(!buroughF&&typeF&&providerF){
            //type and provider
            return  databaseRetriever.queryWifi(StaticVariables.doubleStringQuery(Wifi.tableName, Wifi.columns[2],type,Wifi.columns[3],provider));
        } else if(buroughF&&!typeF&&providerF){
            //provider and burough
            return  databaseRetriever.queryWifi(StaticVariables.doubleStringQuery(Wifi.tableName, Wifi.columns[1],burough,Wifi.columns[3],provider));
        }
        return databaseRetriever.getWifiList();
    }

//    public List<Wifi> getWifiJSFiltered(String burough, String type, String provider)
//    {
//        if (burough.equals("")|| burough.toLowerCase().equals("none")||burough==null) {
//            if (type.equals("")||type.toLowerCase().equals("none")||type==null){
//                if(provider.equals("")||provider.toLowerCase().equals("none")||provider==null){
//                    return databaseRetriever.getWifiList();
//                }
//                return  databaseRetriever.queryWifi(StaticVariables.singleStringQuery(Wifi.tableName, Wifi.columns[3],provider));
//            }
//            return  databaseRetriever.queryWifi(StaticVariables.doubleStringQuery(Wifi.tableName, Wifi.columns[2],type,Wifi.columns[3],provider));
//        }
//        if (type.equals("")||type.toLowerCase().equals("none")||type==null){
//            if (burough.equals("")|| burough.toLowerCase().equals("none")||burough==null) {
//                if(provider.equals("")||provider.toLowerCase().equals("none")||provider==null){
//                    return databaseRetriever.getWifiList();
//                }
//                return  databaseRetriever.queryWifi(StaticVariables.singleStringQuery(Wifi.tableName, Wifi.columns[3],provider));
//            }
//            return  databaseRetriever.queryWifi(StaticVariables.doubleStringQuery(Wifi.tableName, Wifi.columns[1],burough,Wifi.columns[3],provider));
//
//        }
//        if(provider.equals("")||provider.toLowerCase().equals("none")||provider==null){
//            if (type.equals("")||type.toLowerCase().equals("none")||type==null) {
//                if (burough.equals("") || burough.toLowerCase().equals("none")||burough==null) {
//                    return databaseRetriever.getWifiList();
//                }
//                return  databaseRetriever.queryWifi(StaticVariables.singleStringQuery(Wifi.tableName, Wifi.columns[1],burough));
//            }
//            return databaseRetriever.queryWifi(StaticVariables.doubleStringQuery(Wifi.tableName, Wifi.columns[1],burough,Wifi.columns[2],type));
//        }
//        return databaseRetriever.queryWifi(StaticVariables.mapViewWifiQuery(burough,type,provider));
//    }

    public List<Retailer> getRetailerJS() {
        return databaseRetriever.getRetailerList();
    }





}
