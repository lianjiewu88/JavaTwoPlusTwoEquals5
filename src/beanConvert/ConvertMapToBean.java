package beanConvert;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
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

    private static void setProperty(String key, Object value, Type type, Object bean) throws Throwable {
        String methodName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1, key.length());
        // for list, if the setList arg is not ArrayList: NoSuchMethodException
        // lookUp
        Method method = bean.getClass().getMethod(methodName, value.getClass());

        // collectionAdapterFactory
        if (value instanceof List) {
            List valueCopy = ArrayList.class.newInstance();
            Iterator iter = ((List) value).iterator();
            while (iter.hasNext()) {
                // innerbean map
            	// Jerry: here you assume that the list contains Map as item
                Map innerMap = (Map) iter.next();
                ParameterizedType actualType = (ParameterizedType) type;
                // get the actualTypes:generic
                Type[] types = actualType.getActualTypeArguments();
                System.out.println("try to load class: " + types[0].getTypeName());
                Object innerBean = doConvertMapToBean(innerMap, Class.forName(types[0].getTypeName()));
                valueCopy.add(innerBean);
            }
            value = valueCopy;
        }
        
        if( value instanceof Map){
        	System.out.println("Map found!");
        	//ParameterizedType actualType = (ParameterizedType) type;
            // get the actualTypes:generic
            //Type[] types = actualType.getActualTypeArguments();
        	//Object innerBean = doConvertMapToBean((Map)value, Class.forName(types[0].getTypeName()));
        	// Object innerBean = doConvertMapToBean((Map)value, Class.forName(type.getTypeName()));
        	Map valuecopy = (Map)value.getClass().newInstance();
        	Iterator iter = ((Map)value).entrySet().iterator();
        	while (iter.hasNext()){
        		Map.Entry<?, ?> entry = (Map.Entry<?, ?>)iter.next();
        		Object key1 = entry.getKey();
        		Object value1 = entry.getValue(); // HashMap here
        		System.out.println("Jerry key: " + key1 + " value: " + value1);
        		ParameterizedType actualType = (ParameterizedType) type;
                // get the actualTypes:generic
                Type[] types = actualType.getActualTypeArguments();
                System.out.println("try to load class: " + types[0].getTypeName());
                System.out.println("try to load class: " + types[1].getTypeName());
        		Object parsedBean = doConvertMapToBean((Map)value1, Class.forName(types[1].getTypeName()));
        		valuecopy.put(key1, parsedBean);
        	}
        	value = valuecopy;
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
