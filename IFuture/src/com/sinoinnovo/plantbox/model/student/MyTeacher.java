package com.sinoinnovo.plantbox.model.student;

import com.google.gson.annotations.SerializedName;
import com.sinoinnovo.plantbox.model.BaseUser;
import com.sinoinnovo.plantbox.utils.StringUtils;

/**
 * 我的老师实体类
 */
public class MyTeacher extends BaseUser {
	private static final long serialVersionUID = 1L;
	@SerializedName("TeacherRoleName")
	private String roleName;

    private int IsPraised;

    public int getIsPraised() {
        return IsPraised;
    }

    public void setIsPraised(int isPraised) {
        IsPraised = isPraised;
    }

    public String getRoleName() {
		return StringUtils.stringOrEmpty(roleName);
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
