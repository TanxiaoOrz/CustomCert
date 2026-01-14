package com.nickzhang.customcert.data;

import com.baomidou.mybatisplus.annotation.TableId;
import com.nickzhang.customcert.annotation.Column;
import com.nickzhang.customcert.utils.ClassUtils;
import lombok.Data;
import lombok.experimental.Accessors;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @Author: 张骏山
 * @Date: 2026/1/14 15:11
 * @PackageName: com.nickzhang.customcert.data
 * @ClassName: DataProducer
 * @Description: TODO
 * @Version: 1.0
 */
public class DataProducer {
    private final List<FieldInfo> showList = new ArrayList<>();

    private Method getMainIdMethod ;

    private String showName;

    public DataProducer(String showName, Class<?> clazz) {
        this.showName = showName;
        // 列表展示器组装
        Arrays.stream(clazz.getDeclaredFields()).filter(field ->
             field.isAnnotationPresent(Column.class)&&field.getAnnotation(Column.class).listShow()
        )
        .forEach(field -> {
            Method method = ClassUtils.getGetterMethod(clazz, field);
            Column column = field.getAnnotation(Column.class);
            String fieldShowName = column.showName();
            if (fieldShowName.isEmpty()){
                fieldShowName = field.getName();
            }
            showList.add(new FieldInfo()
                    .setName(field.getName())
                    .setShowName(fieldShowName)
                    .setGetterMethod(method)
                    .setFieldType(column.fieldType())
                    .setCanSearch(column.searchShow())
            );
        });

        Arrays.stream(clazz.getDeclaredFields()).filter(field ->
                field.isAnnotationPresent(TableId.class)
        ).findFirst().ifPresentOrElse(
                        field -> {
                            getMainIdMethod = ClassUtils.getGetterMethod(clazz, field);
                        },
                        () -> {
                            getMainIdMethod = ClassUtils.getGetterMethod(clazz, "id");
                        }
                );
    }


}


