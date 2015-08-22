package com.liuliume.common.web.spring.mvc.view;

import java.util.Locale;
import java.util.Map;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
/**
 * �����ͼ��������
 * @author shiqiangguo
 *
 */
public class MultiViewResolver implements ViewResolver {  
	  
    private Map<String, ViewResolver> resolvers;  
  
    @Override  
    public View resolveViewName(String viewName, Locale locale) throws Exception {  
        int n = viewName.lastIndexOf("."); // ��ȡ  
                                            // viewName(modelAndView�е�����)������û���»���  
        String suffix = "";  
  
        // û��Ĭ��ʹ�á�jsp����ʽ ����,�еĻ���ȡ�»��ߺ�����ַ��� ����һ����jsp,ftl,vm�������ļ��е�<entry  
        // key="ftl">��keyƥ��  
        if (n == (-1)) {
            suffix = "jsp";  
        }  
        else {  
            suffix = viewName.substring(n + 1);  
            // ȡ�»���ǰ��Ĳ��� ��ʱ��������Դ��.��������Ҫʹ��hello.jsp ��viewName��Ӧ����hello_jsp  
            viewName = viewName.substring(0, n);  
        }  
  
        // �����»��ߺ�����ַ���ȥ��ȡ�йܵ���ͼ���������  
        ViewResolver resolver = resolvers.get(suffix);
        if (resolver != null) {  
            return resolver.resolveViewName(viewName, locale);  
        }  
        else {  
            return null;  
        }  
    }  
  
    public Map<String, ViewResolver> getResolvers() {  
        return resolvers;  
    }  
  
    public void setResolvers(Map<String, ViewResolver> resolvers) {  
        this.resolvers = resolvers;  
    }  
}  