package beanConvert;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ConvertMapToBean {
    public static Object doConvertMapToBean(Map map, Class<?> bean) throws Throwable {

        // create instance
        Object expectBean = bean.newInstance();

        Field[] fields = bean.getDeclaredFields();
        for (Field field : fields) {
            if (map.containsKey(field.getName())) {
                Type type = field.getGenericType();
                Object value = map.get(field.getName());
                setProperty(field.getName(), map.get(field.getName()), type, expectBean);
            }
        }
        return expectBean;
    }

    private static List parseList(Object value, String className) throws ClassNotFoundException, Throwable{
    	List valueCopy = ArrayList.class.newInstance();
        Iterator iter = ((List)value).iterator();
        while (iter.hasNext()) {
            // innerbean map
        	// Jerry: here you assume that the list contains Map as item
            Map innerMap = (Map) iter.next();
            Object innerBean = doConvertMapToBean(innerMap, Class.forName(className));
            valueCopy.add(innerBean);
        }
        return valueCopy;
    }
    
    private static Map parseMap(Object value, String className) throws ClassNotFoundException, Throwable{
    	Map valuecopy = (Map) value.getClass().newInstance();
    	Iterator iter = ((Map)value).entrySet().iterator();
    	while (iter.hasNext()){
    		Map.Entry<?, ?> entry = (Map.Entry<?, ?>)iter.next();
    		Object key1 = entry.getKey();
    		Object value1 = entry.getValue(); // HashMap here
    		Object parsedBean = doConvertMapToBean((Map)value1, Class.forName(className));
    		valuecopy.put(key1, parsedBean);
    	}
        return valuecopy;
    }
    private static void setProperty(String key, Object value, Type type, Object bean) throws Throwable {
        String methodName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1, key.length());
        Method method = bean.getClass().getMethod(methodName, value.getClass());
        if ( value instanceof Collection){
        	ParameterizedType actualType = (ParameterizedType) type;
            Type[] types = actualType.getActualTypeArguments();
            String className = types[types.length -1].getTypeName();
        	if (value instanceof List) {
                value = parseList(value, className);
            }
        	else if( value instanceof Map){
            	value = parseMap(value, className);
            }
        }
        method.invoke(bean, value);
    }

    public static void main(String[] args) throws Throwable {
        ConvertMapToBean test = new ConvertMapToBean();
        Map valueMap = test.setMapValue();
        MapToBean mapToBean = (MapToBean) test.doConvertMapToBean(valueMap, MapToBean.class);
        System.out.println(mapToBean.toString());
        List<InnerBean> returnList = mapToBean.getInnerList();
        for (InnerBean bean : returnList) {
            System.out.println(bean.getBeanName() + " : " + bean.getBeanNo());
        }
    }

    private Map setMapValue() {
        Map valueMap = new HashMap();
        valueMap.put("name", "vicky");
        valueMap.put("sex", "female");

        Map beanMap = new HashMap();
        beanMap.put("beanName", "innerBean");
        beanMap.put("beanNo", "bean01");

        Map beanMap1 = new HashMap();
        beanMap1.put("beanName", "innerBean1");
        beanMap1.put("beanNo", "bean02");

        List innerList = new ArrayList();
        innerList.add(beanMap);
        innerList.add(beanMap1);

        valueMap.put("innerList", innerList);
        
        Map duridMap = new HashMap();
        duridMap.put("beanName", "Durid");
        duridMap.put("beanNo", "01");

        Map sorMap = new HashMap();
        sorMap.put("beanName", "Sor");
        sorMap.put("beanNo", "02");
        
        Map innerMap = new HashMap();
        innerMap.put("Durid", duridMap);
        innerMap.put("Sor", sorMap);
        
        valueMap.put("innerMap", innerMap);
        return valueMap;
    }
}
