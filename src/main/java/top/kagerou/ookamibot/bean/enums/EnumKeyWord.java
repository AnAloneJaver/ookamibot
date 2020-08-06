package top.kagerou.ookamibot.bean.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum EnumKeyWord {

    GROUP_MENU(1, 701, "菜单"), GROUP_MIRAIFEATURES(1, 702, "Mirai功能"),;

    public Integer keytype;
    public Integer code;
    public String keyword;

    EnumKeyWord(Integer keytype, Integer code, String keyword) {
        this.keytype = keytype;
        this.code = code;
        this.keyword = keyword;
    }

    public static EnumKeyWord groupFind(String keyword) {

        List<EnumKeyWord> collect = Arrays.stream(EnumKeyWord.values()).filter(e -> e.keytype.equals(1))
                .collect(Collectors.toList());
        for (EnumKeyWord enumKeyWord : collect) {
            if (enumKeyWord.keyword.equals(keyword)) {
                return enumKeyWord;
            }
        }
        return null;
    }

    public static EnumKeyWord privateFind(String keyword) {

        List<EnumKeyWord> collect = Arrays.stream(EnumKeyWord.values()).filter(e -> e.keytype.equals(1))
                .collect(Collectors.toList());
        for (EnumKeyWord enumKeyWord : collect) {
            if (enumKeyWord.keyword.equals(keyword)) {
                return enumKeyWord;
            }
        }
        return null;
    }
}
