package com.zmy.laosiji.moudle.entity;

import java.util.List;

/**
 * Created by Michael on 2017/12/21.
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃    神兽保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　 ┣┓
 * 　　　　┃　　　　 ┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 */

public class LoginEntiny {

    /**
     * Data : {"User":{"Id":"30a32fe5-7efe-4419-a457-834f592a9b78","Username":"15820034792","DisplayName":"CarWin","Email":null,"PhoneNumber":"15820034792"},"Token":"189651fd-8419-4fc0-bb8c-def79a2486a2"}
     * HasErrors : false
     * Success : true
     * AllMessages :
     * Messages : []
     */

    private DataBean Data;
    private boolean HasErrors;
    private boolean Success;
    private String AllMessages;
    private List<?> Messages;

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public boolean isHasErrors() {
        return HasErrors;
    }

    public void setHasErrors(boolean HasErrors) {
        this.HasErrors = HasErrors;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public String getAllMessages() {
        return AllMessages;
    }

    public void setAllMessages(String AllMessages) {
        this.AllMessages = AllMessages;
    }

    public List<?> getMessages() {
        return Messages;
    }

    public void setMessages(List<?> Messages) {
        this.Messages = Messages;
    }

    public static class DataBean {
        /**
         * User : {"Id":"30a32fe5-7efe-4419-a457-834f592a9b78","Username":"15820034792","DisplayName":"CarWin","Email":null,"PhoneNumber":"15820034792"}
         * Token : 189651fd-8419-4fc0-bb8c-def79a2486a2
         */

        private UserBean User;
        private String Token;

        public UserBean getUser() {
            return User;
        }

        public void setUser(UserBean User) {
            this.User = User;
        }

        public String getToken() {
            return Token;
        }

        public void setToken(String Token) {
            this.Token = Token;
        }

        public static class UserBean {
            /**
             * Id : 30a32fe5-7efe-4419-a457-834f592a9b78
             * Username : 15820034792
             * DisplayName : CarWin
             * Email : null
             * PhoneNumber : 15820034792
             */

            private String Id;
            private String Username;
            private String DisplayName;
            private Object Email;
            private String PhoneNumber;

            public String getId() {
                return Id;
            }

            public void setId(String Id) {
                this.Id = Id;
            }

            public String getUsername() {
                return Username;
            }

            public void setUsername(String Username) {
                this.Username = Username;
            }

            public String getDisplayName() {
                return DisplayName;
            }

            public void setDisplayName(String DisplayName) {
                this.DisplayName = DisplayName;
            }

            public Object getEmail() {
                return Email;
            }

            public void setEmail(Object Email) {
                this.Email = Email;
            }

            public String getPhoneNumber() {
                return PhoneNumber;
            }

            public void setPhoneNumber(String PhoneNumber) {
                this.PhoneNumber = PhoneNumber;
            }
        }
    }
}
