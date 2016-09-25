package com.wch.test.thistest;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class GsonTest {

	
	public static void main(String[] args) throws JsonSyntaxException, JSONException {
		Gson  gson = new Gson();
	        String result ="{\"data\":[{\"kindCardId\":\"0002303849319bda01497a2d44072219\",\"kindCardName\":\"电子卡\",\"moneyRules\":[{\"id\":\"000230385668fc74015673ae99b77052\",\"isValid\":1,\"opTime\":1473824307706,\"createTime\":1470819637687,\"lastVer\":1,\"kindCardId\":\"0002303849319bda01497a2d44072219\",\"condition\":100.0,\"rule\":20.0,\"entityId\":\"00023038\",\"kindCardName\":\"电子卡\"},{\"id\":\"0002303856df629e015708d26ea8225c\",\"isValid\":1,\"opTime\":1473832208728,\"createTime\":1473321791144,\"lastVer\":1,\"extendFields\":\"\",\"kindCardId\":\"0002303849319bda01497a2d44072219\",\"condition\":101.0,\"rule\":36.0,\"entityId\":\"00023038\",\"kindCardName\":\"电子卡\"},{\"id\":\"000230385711b88e0157225b2f6d13c1\",\"isValid\":1,\"opTime\":1473831010759,\"createTime\":1473750183789,\"lastVer\":1,\"extendFields\":\"\",\"kindCardId\":\"0002303849319bda01497a2d44072219\",\"condition\":1000.0,\"rule\":122.0,\"entityId\":\"00023038\",\"kindCardName\":\"电子卡\"},{\"id\":\"000230385711b83f01572736f79963c8\",\"isValid\":1,\"opTime\":1473831696281,\"createTime\":1473831696281,\"lastVer\":0,\"extendFields\":\"\",\"kindCardId\":\"0002303849319bda01497a2d44072219\",\"condition\":3696.0,\"rule\":36.0,\"entityId\":\"00023038\",\"kindCardName\":\"电子卡\"}]},{\"kindCardId\":\"000230384a09fcfa014a0a5e45bd28c4\",\"kindCardName\":\"贵宾卡\"},{\"kindCardId\":\"000230385668fc7401567d4804d213a8\",\"kindCardName\":\"球星卡\",\"moneyRules\":[{\"id\":\"000230385668fc7201568c01ecda4c81\",\"isValid\":1,\"opTime\":1471227751642,\"createTime\":1471227751642,\"lastVer\":0,\"kindCardId\":\"000230385668fc7401567d4804d213a8\",\"condition\":100.0,\"rule\":50.0,\"entityId\":\"00023038\",\"kindCardName\":\"球星卡\"}]},{\"kindCardId\":\"0002303856df5e1e01570cd484d900bf\",\"kindCardName\":\"hello1\"},{\"kindCardId\":\"000230385711b88e0157231fdc982df9\",\"kindCardName\":\"测试会员卡\"},{\"kindCardId\":\"000230385711b8e50157232f077174de\",\"kindCardName\":\"测试1111\"},{\"kindCardId\":\"000230385711b88e01573c9245af4e97\",\"kindCardName\":\"test我的心\"},{\"kindCardId\":\"000230385711b8e50157410d15d52e9f\",\"kindCardName\":\"测试图片\"}],\"code\":1}\n    ";
            JSONObject jsonObject = new JSONObject(result);
            String resultString = jsonObject.get("data").toString();
            KindCard[] fromJson = gson.fromJson(resultString,KindCard[].class );
	}
 
}
