package com.cyz.util;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.*;

public class MarkdownUtils {

    /**
     * markdown格式转为html格式
     * @param str
     * @return
     */
    public static String markdownToHtml(String str){
        Parser parser=Parser.builder().build();
        Node parse = parser.parse(str);
        HtmlRenderer build = HtmlRenderer.builder().build();
        String string = build.render(parse);
        return string;
    }

    /**
     * 增加扩展（标题锚点，表格生成）
     * @param str
     * @return
     */
    public static String markdownToHtmlExtensions(String str){
     //h标题生成id
        Set<Extension> singleton = Collections.singleton(HeadingAnchorExtension.create());
//转换table为html
        List<Extension> extensions = Arrays.asList(TablesExtension.create());
        Parser parser = Parser.builder()
                .extensions(extensions)
                .build();
        Node parse = parser.parse(str);
        HtmlRenderer renderer = HtmlRenderer.builder()
                .extensions(singleton)
                .extensions(extensions)//下面的这个可以不要
                .attributeProviderFactory(new AttributeProviderFactory() {
                    @Override
                    public AttributeProvider create(AttributeProviderContext attributeProviderContext) {
                        return new CustomAttributeProvider();
                    }
                })
                .build();
          return renderer.render(parse);
    }

    static class CustomAttributeProvider implements AttributeProvider{
        @Override
        public void setAttributes(Node node, String s, Map<String, String> map) {
            //一下实在semantic UI前端框架下作的
            //改变啊标签的target属性为_blank
            if (node instanceof Link) {
            map.put("target","_blank");
            }
            if (node instanceof TableBlock){
                map.put("class","ui celled table");
            }
        }
    }
}
