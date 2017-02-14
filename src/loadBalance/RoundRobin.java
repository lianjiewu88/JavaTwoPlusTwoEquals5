package loadBalance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RoundRobin {
	private static Integer pos = 0;

    public static String getServer()
    {
        // 重建一个Map，避免服务器的上下线导致的并发问题
        Map<String, Integer> serverMap =
                new HashMap<String, Integer>();
        serverMap.putAll(IpMap.serverWeightMap);

        // 取得Ip地址List
        Set keySet = serverMap.keySet();
        ArrayList keyList = new ArrayList();
        keyList.addAll(keySet);

        String server = null;
        synchronized (pos)
        {
            if (pos >= keySet.size())
                pos = 0;
            System.out.println("Position: " + pos + "KeySet size: " + keySet.size());
            server = (String) keyList.get(pos);
            pos ++;
        }

        return server;
    }
    
    public static void main(String[] arg){
    	for( int i = 0; i < 100; i++){
    		System.out.println("Server: " + getServer());
    	}
    }
}
