package service;

import com.alibaba.fastjson.JSONObject;

public interface LoginService {
    JSONObject serve(String account, String password);
}
