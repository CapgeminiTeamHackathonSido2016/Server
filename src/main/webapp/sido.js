function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)", "i"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}

var latitude = getParameterByName('latitude');
var longitude = getParameterByName('longitude');

//Get the current position
    function fGetCurrentPosition()
    {
        //get position from GPS
        return { coords : {lon: longitude, lat: latitude}};
    };
    
    var myMap = null;

    function fGetBestConf(conf) {
       var useragent = navigator.userAgent;
       if(useragent.indexOf('iPhone') != -1 || useragent.indexOf('Android') != -1 || useragent.indexOf('iPad') != -1 ) {
        conf.container.style.width = '100%';
        conf.container.style.height = '100%';
        conf.skin = ViaMichelin.Api.Constants.Map.SKIN.MOBILE.SMALL;
        //Display geolocation control
        conf.geolocationControl = true;   
        if(useragent.indexOf('iPad') != -1){
         conf.skin = ViaMichelin.Api.Constants.Map.SKIN.MOBILE.LARGE;
        }
       }else{  
        conf.skin = ViaMichelin.Api.Constants.Map.SKIN.DEFAULT;
        //Display service POIs selector
        conf.menuPoiControl = true;
        //Display only Service station layer
        conf.menuPoiControlOptions = {mode:ViaMichelin.Api.Constants.Map.POI.MODE.MORE, layer:['STS']};
        //Display situation map
        conf.situationMapControl =false;
       }
       return conf;
      };//fGetBestConf

    

function fLaunchSearch(itiIdx){{
         currentItiIdx = itiIdx;
         //Initialize map
         if(null == myMap){
          VMLaunch("ViaMichelin.Api.Map",{ 
            container : $_id("map_container"),//Map container (DOM element)     
            //Initializes map center with data from another service called in the onSuccess callback
            center : ViaMichelin.Api.Constants.Map.DELAY_LOADING,
            mapTypeControl : true
            },{
            onInit: function(serviceMap){
             myMap = serviceMap;
            },
            onSuccess: function(){
             fLaunchRoutePlanner(itiIdx);
            }});//Initial zoom level
         }else{//Clean up
          
          }
          fLaunchRoutePlanner(itiIdx);
         }
        };//fLaunchSearch

        // Fucntion pour calculer l'itinéare d'un point à un autre 
        function fLaunchRoutePlanner(itiIdx){
           //Launch itinerary computation
           VMLaunch("ViaMichelin.Api.Itinerary", {
             steps:[//Array of Geo coodinates
              {address: {
              city: "lyon",
              countryISOCode: "fra"}},
            {address: {
              city: "paris", 
              countryISOCode: "fra"}}
              ],

             itit: ViaMichelin.Api.Constants.Itinerary.ITINERARY_TYPE.RECOMMENDED,
             favMotorways: false,
             avoidBorders: false,
             avoidTolls: false,
             avoidCCZ: false,
             avoidORC: false,
             veht:ViaMichelin.Api.Constants.Itinerary.VEHICULE_TYPE.TRUCK,
             //Map to display itinerary trace with automatic redraw to fit iti
             map: {container: $_id("map_container"), focus: true},

             roadsheet: $_id("dRoadsheet"),
             multipleIti: false,
             distUnit: ViaMichelin.Api.Constants.Itinerary.DIST_UNIT.MILES
            },{
             onSuccess : function (result) {
              if(0 == currentItiIdx){
               //Loop through summaries to extract multiple itineraries
               var out = 'Suggested routes: <select onchange="fLaunchSearch(this.value)">';
               for (var itiIdx = 0; itiIdx < result.header.summaries.length; ++itiIdx){
                var name = '';
                if(0 == result.header.summaries[itiIdx].names.length){
                 out += '<option value="0"/>No name because only one route</option>';
                }else{
                 //Concatenate all names
                 for (var i = 0; i < result.header.summaries[itiIdx].names.length; ++i){
                  name += result.header.summaries[itiIdx].names[i] + ' ';
                 }
                 out += '<option value="' + itiIdx + '"/>' + name + '</option>';
                }
               }
               out += '</select>';
               $_id("dMultipleIti").innerHTML = out;
              }else{
               $_id("dMultipleIti").value = currentItiIdx;
              }
              $_id("dSummary").innerHTML = "<br/>Distance : " +  result.header.summaries[currentItiIdx].totalDist.toFixed(1) + 'mi<br/>Time : ' + result.header.summaries[0].totalTime + 's';
             },
             onError : function (error) {
              alert('Whoops' + error);
             }});
          };//fLaunchRoutePlanner


var lati = null;
var longi = null;

function fLoadMap(){
       var commonConf = {//Service parameters
        //Map container (DOM element)
        container : $_id("map_container"),
        //Initial geographical coordinates of the map center
        center : fGetCurrentPosition(),
        //You can also use an address
        //center : {address:{city:"Paris", countryISOCode: "FRA"}},
        //You can also use a locID (already includes the best zoom level)
        //center : {locId:"31NDFhcWsxMGNOVEV1TlRBd01qUT1jTFRBdU1USTNNRFk9"},
        //You can also use a (dbID, poiID) pair (which implicitely includes the best zoom level)
        //center : {poi:{db:163978,id=ND1216}},
        //You can also use current position (from a GPS sensor)
        //center : ViaMichelin.Api.Constants.Map.FROM_GEOLOCATION,
        //Initial zoom level. See onSuccess below if center is a locID or a poiID
        zoom : 12,
        markerControl:true,
        tileOpacity:1,
        //Display map type selector
        mapTypeControl : true,
        //Set map type
        mapTypeControlOptions : {type: ViaMichelin.Api.Constants.Map.TYPE.LIGHT},
        //Display geolocation selector
        geolocationControl : true
       };
       VMLaunch("ViaMichelin.Api.Map", fGetBestConf(commonConf),{
         onInit: function(serviceMap){
          myMap = serviceMap;
         },
         onClick: function(event)
         {
          //Display current mouse coordinates
          lati = event.lat.toFixed(5);
          longi = event.lon.toFixed(5);

          $_id("position").innerHTML = "Center geo-coordinates of the map are (" + event.lon.toFixed(5)  + ', ' + event.lat.toFixed(5) + ')';
         },
         onInitError: function(){
          alert('Whoops Map cannot be loaded!');
         }/*,
        //If center is a locID or a (dbID, poiID) pair, you must uncomment this section
        //to set the zoom level defined in the zoom parameter
        onSuccess: function(){
          this.refresh();
        }*/
        });
      };//fLoadMap 


function fLaunchPoiSearch(){
 //Launch proximity search
 VMLaunch("ViaMichelin.Api.Poi", {
   service: ViaMichelin.Api.Constants.Poi.SERVICE_TYPE.FIND_POI,
   db: "170745",//Database ID
   center: {address:{ //A geocoding is done behind the scene
    address: "225",  
    zip: "",
    city: "lyon",
    countryISOCode: "fra"
   }},//Search center
   nb: 5,//Number of returned POIs
   map: { container: $_id("map_container"), 
      focus: true, 
      offset : {x : 30, y: 0},
      iconPath: "mapiconpoi{ICON_ID}.png"}
   },{
    onSuccess : function (results) {
     POIs = results;
     var out = '<table id="tPoiList">';
     for(var i = 0; i< results.nbFound; ++i){
      out += '<tr><td><b>' + (i+1) + '</b></td><td>' + results.poiList[i].poi.name + '</td><td>' + results.poiList[i].dist + 'm</td><td>' + results.poiList[i].poi.id + '</td><td>';
      var metanums = results.poiList[i].poi.datasheet.metanumList;
      var imgPath = results.poiList[i].poi.datasheet.imgPath;
      for(var j = 1; j< metanums.length-1; ++j){
       if(metanums[j].value != 0){
        //We get metanums only with related icons
        //The meaning of each value is defined by the client.
        out += '<img alt="" src="http://apijsv2.viamichelin.com/apijsv2/' + imgPath + metanums[j].idx +'_' + metanums[j].value + '.gif"/>';
       }
      }
      out += '</td></tr>';
     }
     out += '</table><br/><input type="button" value="Export" onclick="fExportPOIs();"/>';
     $_id("map_container").innerHTML = out;
    },
    onError : function (error) {
     alert('Whoops' + error);
    }});    
};//fLaunchPoiSearch

function fExportPOIs(){
 if(null != POIs){
  //Launch GPS export of returned POI list
  VMLaunch("ViaMichelin.Api.Export", {
     typeExport: document.getElementById("strGPSFormat").value,
     data: POIs
    },{
     onSuccess : function (result) {
      //We get a url so open it
      window.open(result.url,'null','height=10,width=10,status=no, toolbar=no,menubar=no,location=no')
     },
     onError : function (error) {
      alert('Whoops' + error);
     }});
 }
};//fExportPOIs
