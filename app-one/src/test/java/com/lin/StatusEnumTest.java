package com.lin;

import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import com.lin.common.StatusEnum;
import com.lin.utils.DictionaryUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StatusEnumTest {

    @Test
    void test() {
        StatusEnum statusEnum = StatusEnum.PROCESSING;
        String key = JSONObject.toJSONString(statusEnum);
        Assertions.assertEquals("1", key);

        int k = 2;
        StatusEnum finished = JSONObject.parseObject(Integer.toString(k), StatusEnum.class);
        Assertions.assertEquals(StatusEnum.FINISHED, finished);

        int finalK = 3;
        Assertions.assertThrows(JSONException.class, () -> JSONObject.parseObject(Integer.toString(finalK), StatusEnum.class));

        DictionaryUtil.getDictionaryList(StatusEnum.class).forEach(System.out::println);
    }
}
