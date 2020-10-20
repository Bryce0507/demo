package com.dingding.web;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.Document;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author zxb
 * @date 2020/3/4 9:55
 */
@RestController
@RequestMapping("file")
public class FileToPdfController {



/*    @RequestMapping("fileToPdf")
    public void fileToPdf(@RequestParam File file) throws IOException {
        String fileName = file.getName();
        excel2Pdf("D:\\ss\\"+fileName,"D:\\scpdf\\"+fileName.replace(".xls","")+".pdf");

    }*/

    static final int wdFormatPDF = 17;// PDF 格式
    /**
     * word转化为PDF
     * @param sfileName
     * @param toFileName
     * @return
     * @throws Exception
     * @author ygl
     */
    public static int word2Pdf(String sfileName,String toFileName) throws Exception{

        System.out.println("启动Word...");
        long start = System.currentTimeMillis();
        ActiveXComponent app = null;
        Dispatch doc = null;
        try {
            app = new ActiveXComponent("Word.Application");
            app.setProperty("Visible", new Variant(false));
            // 打开word文件
            Dispatch docs = app.getProperty("Documents").toDispatch();
//          doc = Dispatch.call(docs,  "Open" , sourceFile).toDispatch();
            doc = Dispatch.invoke(docs,"Open",Dispatch.Method,new Object[] {
                    sfileName, new Variant(false),new Variant(true) }, new int[1]).toDispatch();
            System.out.println("打开文档..." + sfileName);
            System.out.println("转换文档到PDF..." + toFileName);
            File tofile = new File(toFileName);
            // System.err.println(getDocPageSize(new File(sfileName)));
            if (tofile.exists()) {
                tofile.delete();
            }
//          Dispatch.call(doc, "SaveAs",  destFile,  17);
            // 作为html格式保存到临时文件：：new Variant(8)其中8表示word转html;7表示word转txt;44表示Excel转html;17表示word转成pdf。
            Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] {
                    toFileName, new Variant(17) }, new int[1]);
            long end = System.currentTimeMillis();
            System.out.println("转换完成..用时：" + (end - start) + "ms.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("========Error:文档转换失败：" + e.getMessage());
        }catch(Throwable t){
            t.printStackTrace();
        } finally {
            // 关闭word
            Dispatch.call(doc,"Close",false);
            System.out.println("关闭文档");
            if (app != null)
                app.invoke("Quit", new Variant[] {});
        }
        //如果没有这句winword.exe进程将不会关
        ComThread.Release();
        return 1;
    }

    /**
     * excle转PDF
     * @param inFilePath
     * @param outFilePath
     * @author ygl
     */
    public static void excel2Pdf(String inFilePath, String outFilePath) throws Exception {
        ActiveXComponent ax = null;
        Dispatch excel = null;
        try {
            ComThread.InitSTA();
            ax = new ActiveXComponent("Excel.Application");
            ax.setProperty("Visible", new Variant(false));
            ax.setProperty("AutomationSecurity", new Variant(3)); // 禁用宏
            Dispatch excels = ax.getProperty("Workbooks").toDispatch();
            Object[] obj = new Object[]{
                    inFilePath,
                    new Variant(false),
                    new Variant(false)
            };
            excel = Dispatch.invoke(excels, "Open", Dispatch.Method, obj, new int[9]).toDispatch();
            File tofile = new File(outFilePath);
            // System.err.println(getDocPageSize(new File(sfileName)));
            if (tofile.exists()) {
                tofile.delete();
            }
            // 转换格式
            Object[] obj2 = new Object[]{
                    new Variant(0), // PDF格式=0
                    outFilePath,
                    new Variant(0)  //0=标准 (生成的PDF图片不会变模糊) ; 1=最小文件
            };
            Dispatch.invoke(excel, "ExportAsFixedFormat", Dispatch.Method,obj2, new int[1]);
            System.out.println("转换完毕！");
        } catch (Exception es) {
            es.printStackTrace();
            throw es;
        } finally {
            if (excel != null) {
                Dispatch.call(excel, "Close", new Variant(false));
            }
            if (ax != null) {
                ax.invoke("Quit", new Variant[] {});
                ax = null;
            }
            ComThread.Release();
        }
    }
    public static void main(String[] args) throws Exception {
//        word2Pdf("C:\\Users\\Administrator\\Desktop\\1234.xls", "C:\\Users\\Administrator\\Desktop");
        excel2Pdf("C:\\Users\\Administrator\\Desktop\\1234.xls", "C:\\Users\\Administrator\\Desktop\\1234.pdf");
    }





}
