package com.sf.utils.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ExtractJsonHeaders {

	public ExtractJsonHeaders() {
		// TODO Auto-generated constructor stub
	}
	
//	public static Map<String, Object> parseJSONToMap(String msgData) {
//	    JSONParser parser = new JSONParser();
//	    ContainerFactory containerFactory = new ContainerFactory(){
//	        @Override
//	        public Map createObjectContainer() {
//	            return new LinkedHashMap();
//	        }
//
//	        @Override
//	        public List creatArrayContainer() {
//	            return null;
//	        }
//	    };
//	    try {
//	        return (Map<String,Object>)parser.parse(msgData, containerFactory);
//	    } catch (ParseException e) {
//	       System.err.println("Exception parsing JSON string {}"+ msgData + e);
//	    }
//	    return null;
//	}  

	public static void main(String[] args) {
		String jsonStr = "{\"TS\":\"1536062644\",\"XFF\":null,\"IP\":null,\"KUID\":\"MMEuVR6A\",\"DNT\":\"-\",\"UA\":null,\"REF\":\"https://www.immobilienscout24.de/Suche/S-T/P-74/Haus-Kauf/Mecklenburg-Vorpommern\",\"HOST\":\"beacon.krxd.net\",\"PATH\":\"/pixel.gif\",\"QS\":\"?request_id=f6246eb4-5704-4357-8dbd-c759670dc1ad&source=smarttag&fired=report&confid=Jvmg-SwI&_kpid=c819846f-b6e4-4ef4-b357-28399862b473&_kcp_s=ImmobilienScout24&_kcp_d=www.immobilienscout24.de&_knifr=14&rtsegs=puawho483&_kua_kx_lang=en-us&_kua_kx_tech_browser_language=en-us&_kua_is24_hour=8&_kua_is24_hour_range=5-8&_kua_is24_psn_iphash=db8e4f6c4238f1a8ee405983e4a3b8b5dba3e6ea1aef6235fb00a4915b72d335&_kua_is24_userid=a-625caa2c2e944fc084daed7651753114&_kua_kx_whistle=0&_kpa_as_dart_makeDELIM=%2C&_kpa_as_dart_artDELIM=%2C&_kpa_is24_query_realEstateTypeDELIM=%2C&_kpa_is24_pag_pagetitleDELIM=%2C&_kpa_domain=www.immobilienscout24.de&_kpa_url_path_1=Suche&_kpa_url_path_2=S-T&_kpa_url_path_3=P-74&_kpa_url_path_4=Haus-Kauf&_kpa_url_path_5=Mecklenburg-Vorpommern&_kpa_time_of_day=undefined&_kpa_is24_svc_module_name=resultlist&_kpa_is24_svc_tm_version=V1.111&_kpa_is24_pag_pagetitle=is24.de.finden.wohnen.haus_kauf.result&_kpa_is24_ga_cd_cxp_abtest=LCMS4181%3Dheader-2018&_kpa_is24_KVcourtage=y&_kpa_is24_ga_cd_test_cxp_2=BAU3989%3DOFF%3BPDEV1789%3DOFF%3BADS1406%3DON&_kpa_is24_ga_cd_cxp_historicallisting=false&_kpa_is24_query_courtage=y&_kpa_is24_query_regio1=Mecklenburg_Vorpommern&_kpa_is24_ga_cd_cxp_resultview=listview&_kpa_is24_query_timestamp=09.2018&_kpa_is24_KVregio1=Mecklenburg_Vorpommern&_kpa_is24_KVimmotype=HOUSE_BUY&_kpa_is24_query_realEstateType=HOUSE_BUY&_kpa_is24_timestamp=09.2018&_kpa_is24_meta.msapplication-config=none&_kpa_is24_meta.format-detection=telephone%3Dno&_kpa_is24_meta.viewport=width%3Ddevice-width%2C%20initial-scale%3D1%2C%20minimum-scale%3D1%2C%20maximum-scale%3D1&_kpa_is24_meta.robots=noindex%2C%20follow&_kpa_is24_meta.description=Mecklenburg-Vorpommern%3A%20Haus%20kaufen%20in%20Mecklenburg-Vorpommern.%20Bei%20Immobilien%20Scout24%20finden%20Sie%20passende%20Angebote%20zu%20H%C3%A4user%20kaufen%20oder%20Haus%20zum%20Kauf%20in%20Mecklenburg-Vorpommern.&_kpa_is24_meta.keywords=Haus%20kaufen%20Mecklenburg-Vorpommern%2C%20H%C3%A4user%20kaufen%20Mecklenburg-Vorpommern&_kpa_is24_dom.title=Haus%20kaufen%20Mecklenburg-Vorpommern%3A%20H%C3%A4user%20kaufen%20in%20Mecklenburg-Vorpommern%20bei%20Immobilien%20Scout24&_kpa_is24_dom.domain=www.immobilienscout24.de&_kpa_is24_dom.viewport_height=1024&_kpa_is24_dom.viewport_width=1280&_kpa_is24_ut.domain=immobilienscout24.de&_kpa_is24_ut.version=ut4.42.201809040756&_kpa_is24_ut.event=view&_kpa_is24_ut.visitor_id=0165a477d18300194b29242d9cad00055005004d00918&_kpa_is24_ut.account=immobilienscout&_kpa_is24_ut.profile=is24&_kpa_is24_ut.env=prod&_kpa_is24_country_code=de&_kpa_is24_site_domain=immobilienscout24.de&_kpa_is24_screen_type_of_device=default&_kpa_is24_om_mobile_notmobile=66472&_kpa_is24_aff_conv_einzel=einzel&_kpa_is24_aff_conv_einzel_proz=einzel_proz&_kpa_is24_aff_conv_ibw=ibw&_kpa_is24_aff_conv_lead_registrierung=lead_registrierung&_kpa_is24_aff_conv_lead_schnellkontakt=lead_schnellkontakt&_kpa_is24_aff_conv_myscout=myscout&_kpa_is24_aff_conv_schnellkontakt=schnellkontakt&_kpa_is24_aff_conv_eigentuemer_basis=eigentuemer_basis&_kpa_is24_aff_conv_lead_umzugsangebot=lead_umzugsangebot&_kpa_is24_aff_conv_umzug=umzug&_kpa_is24_aff_conv_bing=2424&_kpa_is24_aff_conv_boni=bonicheck&_kpa_is24_cachebuster=1835615332238376200&_kpa_is24_om_default_sid=10745&_kpa_is24_platform=desktop&_kpa_is24_platform_environment=immobilienscout24.de%3Adesktop%3Aprod&_kpa_is24_ga_sitespeedsamplerate=100&_kpa_is24_pag_dg1=wohnen&_kpa_is24_pag_dg2=haus_kauf&_kpa_is24_pag_dg3=result&_kpa_is24_randomVisitorId=6851536062640538&_kpa_is24_evt_ozahl=3051&_kpa_is24_evt_sart=el_neu&_kpa_is24_obj_ozahl=3051&_kpa_is24_obj_ityp=haus_kauf&_kpa_is24_geo_land=deutschland&_kpa_is24_evt_event=27&_knopii=1&t_navigation_type=0&t_dns=0&t_tcp=0&t_http_request=-1&t_http_response=643&t_content_ready=2462&t_window_load=7651&t_redirect=0&interchange_ran=false&userdata_was_requested=true&userdata_did_respond=true&store_user_after=tacnvvz0i&store_segs_after=puawho483&_kurl_=https%3A%2F%2Fwww.immobilienscout24.de%2FSuche%2FS-T%2FHaus-Kauf%2FMecklenburg-Vorpommern&userdata_user=MMEuVR6A%2Ctacnvvz0i&browser_bucket=Other&browser_version=0&sview=1&kplt0=20711&kplt1=18329&kplt2=18331&kplt3=18336&kplt5=18363&kplt6=20380&kplt7=20381&kplt8=18330&kplt9=20712&kplt10=28352&kplt11=28648&kplt12=30563&kplt13=30564&kplt14=30565&kplt15=35216&jsonp_requests=https%3A%2F%2Fconsumer.krxd.net%2Fconsent%2Fget%2Fc819846f-b6e4-4ef4-b357-28399862b473%2C42%2Chttps%3A%2F%2Fconsumer.krxd.net%2Fconsent%2Fset%2Fc819846f-b6e4-4ef4-b357-28399862b473%2C409%2Chttps%3A%2F%2Fbeacon.krxd.net%2Fcookie2json%2C382%2Chttps%3A%2F%2Fbeacon.krxd.net%2Foptout_check%2C103%2Chttps%3A%2F%2Fcdn.krxd.net%2Fuserdata%2Fget%2C100\",\"RESP\":\"204\",\"RT\":\"41\",\"NEWUSER\":\"0\",\"NODE\":\"beacon-n005-ash.krxd.net\",\"V\":\"8\",\"PROTOCOL\":\"https\"}";
		
//		LinkedHashMap<String, Object> map = parseJSONToMap(jsonStr);
//		
//		Set<String> keyset = map.keySet();
//		Iterator<String> keyIter = keyset.iterator();
//		while(keyIter.hasNext()) {
//			System.out.println(keyIter.next() + " string,");
//		}
		try {
			
			JSONObject obj = (JSONObject) new JSONParser().parse(jsonStr);
			Set<String> keyset = obj.keySet();
			Iterator<String> keyIter = keyset.iterator();
			while(keyIter.hasNext()) {
				System.out.println(keyIter.next() + " string,");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
