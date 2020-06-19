package com.example.demo.utils;

import cn.hutool.json.JSONUtil;
import org.bson.Document;

public class MongoUtil {

    public static <T> Document objToDoc(T obj){
        return Document.parse(JSONUtil.toJsonStr(obj));
    }

    public static <T> T docToObj(Document doc, Class<T> cls){
        return JSONUtil.toBean(doc.toJson(), cls);
    }

}
