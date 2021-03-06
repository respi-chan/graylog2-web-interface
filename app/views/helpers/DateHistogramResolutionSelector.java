package views.helpers;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.text.WordUtils;

import lib.SearchTools;
import play.mvc.Http;

import java.util.Map;

public class DateHistogramResolutionSelector {

	public static String getOptions(String selected, Http.Request request) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (String interval : SearchTools.ALLOWED_DATE_HISTOGRAM_INTERVALS) {
			if (interval.equals(selected)) { sb.append("<strong>"); }

            StringBuilder url = new StringBuilder(request.path());

            Map<String, String[]> queryParams = Maps.newHashMap(request.queryString());
            String[] qryInterval = {interval};
            queryParams.put("interval", qryInterval);

            url.append("?");

            for (Map.Entry<String, String[]> entry : queryParams.entrySet()) {
                for (String value : entry.getValue()) {
                    url.append(entry.getKey())
                            .append("=")
                            .append(value)
                            .append("&");
                }
            }

            String finalUrl = url.substring(0, url.length()-1);

            sb.append("<a href='").append(finalUrl).append("' class='date-histogram-res-selector' data-resolution='").append(interval).append("'>");
			sb.append(WordUtils.capitalize(interval));
			sb.append("</a>");
			
			if (interval.equals(selected)) { sb.append("</strong>"); }
			
			if (i != SearchTools.ALLOWED_DATE_HISTOGRAM_INTERVALS.size()-1) {
				sb.append(", ");
			}
				
			i++;
		}
		
		return sb.toString();
	}
	
}
