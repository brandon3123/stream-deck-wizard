package enums.name.action;

public enum ActionName {
    OPEN_FOLDER("Open Folder"),
    CREATE_FOLDER("Create Folder"),
    MULTI_ACTION("Multi Action"),
    ACTION_0_0(ActionNameConstant.ACTION_0_0),
    ACTION_0_1(ActionNameConstant.ACTION_0_1),
    ACTION_0_2(ActionNameConstant.ACTION_0_2),
    ACTION_1_0(ActionNameConstant.ACTION_1_0),
    ACTION_1_1(ActionNameConstant.ACTION_1_1),
    ACTION_1_2(ActionNameConstant.ACTION_1_2),
    ACTION_2_0(ActionNameConstant.ACTION_2_0),
    ACTION_2_1(ActionNameConstant.ACTION_2_1),
    ACTION_2_2(ActionNameConstant.ACTION_2_2),
    ACTION_3_0(ActionNameConstant.ACTION_3_0),
    ACTION_3_1(ActionNameConstant.ACTION_3_1),
    ACTION_3_2(ActionNameConstant.ACTION_3_2),
    ACTION_4_0(ActionNameConstant.ACTION_4_0),
    ACTION_4_1(ActionNameConstant.ACTION_4_1),
    ACTION_4_2(ActionNameConstant.ACTION_4_2);

    private String name;

    ActionName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static class ActionNameConstant {
        public static final String ACTION_0_0 = "0,0";
        public static final String ACTION_0_1 = "0,1";
        public static final String ACTION_0_2 = "0,2";
        public static final String ACTION_1_0 = "1,0";
        public static final String ACTION_1_1 = "1,1";
        public static final String ACTION_1_2 = "1,2";
        public static final String ACTION_2_0 = "2,0";
        public static final String ACTION_2_1 = "2,1";
        public static final String ACTION_2_2 = "2,2";
        public static final String ACTION_3_0 = "3,0";
        public static final String ACTION_3_1 = "3,1";
        public static final String ACTION_3_2 = "3,2";
        public static final String ACTION_4_0 = "4,0";
        public static final String ACTION_4_1 = "4,1";
        public static final String ACTION_4_2 = "4,2";
    }
}