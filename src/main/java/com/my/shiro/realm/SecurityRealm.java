package com.my.shiro.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.my.shiro.bo.UserService;
import com.my.shiro.entity.Permission;
import com.my.shiro.entity.Role;
import com.my.shiro.entity.User;

public class SecurityRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	/**
	 * Ϊ��ǰ��¼��Subject�����ɫ��Ȩ��
	 * 
	 * @see ������:�����и÷����ĵ���ʱ��Ϊ����Ȩ��Դ������ʱ
	 * @see ������:����ÿ�η�������Ȩ��Դʱ����ִ�и÷����е��߼�,�����������Ĭ�ϲ�δ����AuthorizationCache
	 * @see ���˸о���ʹ����Spring3
	 *      .1��ʼ�ṩ��ConcurrentMapCache֧��,����������Ƿ�����AuthorizationCache
	 * @see ����˵��������ݿ��ȡȨ����Ϣʱ,��ȥ����Spring3.1�ṩ�Ļ���,����ʹ��Shior�ṩ��AuthorizationCache
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		// Ϊ��ǰ�û����ý�ɫ��Ȩ��
		SimpleAuthorizationInfo simpleAuthorInfo = null;
		// ��ȡ��ǰ��¼���û���
		/*String currentUsername = (String) super
				.getAvailablePrincipal(principals);*/
		User user = (User) super
				.getAvailablePrincipal(principals);
		List<String> roleList = new ArrayList<String>();
		List<String> permissionList = new ArrayList<String>();

		//User user = userService.getByUserName(currentUsername);
		if (null != user) {
			// ʵ����User�а������û���ɫ��ʵ������Ϣ
			if (null != user.getRoles() && user.getRoles().size() > 0) {
				for (Role role : user.getRoles()) {
					roleList.add(role.getName());
					// ʵ����Role�а����н�ɫȨ�޵�ʵ������Ϣ
					if (null != role.getPermissions()
							&& role.getPermissions().size() > 0) {
						for (Permission permission : role.getPermissions()) {
							if (!StringUtils.isEmpty(permission.getUrl())) {
								permissionList.add(permission.getUrl());
							}
						}
					}
				}
				simpleAuthorInfo = new SimpleAuthorizationInfo();
				simpleAuthorInfo.addRoles(roleList);
				simpleAuthorInfo.addStringPermissions(permissionList);
			}
		}
		return simpleAuthorInfo;
	}

	/**
	 * ��֤��ǰ��¼��Subject
	 * 
	 * @see ������:�����и÷����ĵ���ʱ��ΪLoginController.login()������ִ��Subject.login()ʱ
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		AuthenticationInfo authcInfo = null;
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		System.out.println("��֤��ǰSubjectʱ��ȡ��tokenΪ"
				+ ReflectionToStringBuilder.toString(token,
						ToStringStyle.MULTI_LINE_STYLE));
		User user = userService.getByUserName(token.getUsername());
		if (null != user) {
			authcInfo = new SimpleAuthenticationInfo(user,
					user.getPassword(), user.getNickName());
			this.setSession("currentUser", user);
		}
		return authcInfo;
	}

	private void setSession(Object key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			System.out
					.println("SessionĬ�ϳ�ʱʱ��Ϊ[" + session.getTimeout() + "]����");
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}
}
