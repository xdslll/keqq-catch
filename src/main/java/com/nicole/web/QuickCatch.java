package com.nicole.web;

import com.google.gson.Gson;
import com.nicole.web.model.Orgnization;
import com.nicole.web.model.Result;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiads
 * @date 11/01/2018
 * @since
 */
public class QuickCatch {

    public static void main(String[] args) throws Exception {

        //File excelFile = new File("/Users/apple/Desktop/1.xlsx");
        File excelFile = new File("C:\\Users\\Administrator\\Desktop\\1.xlsx");

        //创建Excel文件
        ExcelUtil excelUtil = new ExcelUtil();
        XSSFWorkbook workbook = excelUtil.createWorkBook(excelFile);
        XSSFSheet sheet = excelUtil.getSheet(workbook, 0);
        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();
        int totalRowNum = (lastRowNum - firstRowNum) + 1;
        Row firstRow = sheet.getRow(firstRowNum);
        int firstCellNum = firstRow.getFirstCellNum();
        int lastCellNum = firstRow.getLastCellNum();
        int totalCellNum = (lastCellNum - firstCellNum) + 1;
        System.out.println("excel读取成功：共" + totalRowNum + "行，共" + totalCellNum + "列，第一行行号：" + firstRowNum + "，第一列列号：" + firstCellNum);

        //从第三行开始
        firstRowNum = 2;
        //在最后一列后面再增加两列
        excelUtil.writeCell(sheet, firstRowNum, lastCellNum + 1, "住所");
        excelUtil.writeCell(sheet, firstRowNum, lastCellNum + 2, "登记日期(regDate)");
        excelUtil.writeCell(sheet, firstRowNum, lastCellNum + 3, "发证日期(signDate)");
        excelUtil.writeCell(sheet, firstRowNum, lastCellNum + 4, "有效日期起始(signDateBegin)");
        excelUtil.writeCell(sheet, firstRowNum, lastCellNum + 5, "有效日期终止(signDateEnd)");
        totalCellNum += totalRowNum + 2;
        firstRowNum++;

        for (int i = firstRowNum; i < totalRowNum; i++) {
            System.out.println("正在处理第" + (i + 1) + "行");
            //读取第二列社会组织名称
            Row row = sheet.getRow(i);
            String orgType = row.getCell(0).getStringCellValue().trim();
            String orgName = row.getCell(1).getStringCellValue().trim();
            String orgCode = row.getCell(2).getStringCellValue().trim();
            System.out.println("社会组织名称为:" + orgName + "，社会统一信用代码为:" + orgCode);

            //通过POST请求数据
            String url = "http://59.201.8.162:6888/camsjs/command/ajax/com.inspur.cams.sorg.manage.cmd.SomCertQueryCmd";
            String key1 = "民办非企业";
            String param1 = "{\"params\":{\"javaClass\":\"org.loushang.next.handler.ParameterSet\",\"map\":{\"SOM_CERT.unified_Code@like\":\"" + orgCode + "\",\"SOM_CERT.cert_Status@=\":\"1\",\"SOM_CERT.cert_Type@=\":\"0\",\"MORG_AREA\":\"056BA3C52BC645209958D0DECFD8113B\",\"sorg_Type\":\"M\",\"start\":0,\"limit\":10,\"defaultSort\":{\"javaClass\":\"ArrayList\",\"_list\":[]},\"dir\":\"ASC\",\"needTotal\":true},\"length\":14},\"context\":{\"javaClass\":\"HashMap\",\"map\":{},\"length\":0}}";

            String key2 = "社会团体";
            String param2 = "{\"params\":{\"javaClass\":\"org.loushang.next.handler.ParameterSet\",\"map\":{\"SOM_CERT.unified_Code@like\":\"" + orgCode + "\",\"SOM_CERT.cert_Status@=\":\"1\",\"SOM_CERT.cert_Type@=\":\"0\",\"MORG_AREA\":\"056BA3C52BC645209958D0DECFD8113B\",\"sorg_Type\":\"S\",\"start\":0,\"limit\":10,\"defaultSort\":{\"javaClass\":\"ArrayList\",\"_list\":[]},\"dir\":\"ASC\",\"needTotal\":true},\"length\":14},\"context\":{\"javaClass\":\"HashMap\",\"map\":{},\"length\":0}}";
            //System.out.println(url);
            //System.out.println(param);

            String html = null;
            if (orgType.equals(key1)) {
                html = post(url, param1);
            } else if (orgType.equals(key2)){
                html = post(url, param2);
            }
            if (html == null) {
                continue;
            }
            //String testHtml = "{\"total\":1,\"javaClass\":\"DataSet\",\"success\":true,\"metaData\":{\"totalProperty\":\"total\",\"root\":\"rows\",\"successProperty\":\"success\"},\"rows\":[{\"receiveDate\":\"\",\"sorgCode\":\"1123039\",\"sorgType\":\"M\",\"certType\":\"0\",\"signBeginDate\":\"2014-09-22\",\"state\":0,\"sorgName\":\"南京鼓楼青岛路社区水电维修服务队\",\"unifiedCode\":\"52320106MJ56850014\",\"borgName\":\"南京市鼓楼区人民政府湖南路办事处\",\"taskCode\":\"\",\"business\":\"对辖区居民进行免费水电维修服务\",\"signOrgan\":\"鼓楼区民政局\",\"id\":\"ff8080815f6fd768015f719b61b3716c\",\"sorgId\":\"ff80808156c3717c0156c490e75218ba\",\"signDate\":\"2014-09-22\",\"sorgKind\":\"1\",\"printPeople\":\"\",\"ifReceive\":\"\",\"signPeriod\":5,\"issueReasonDesc\":\"\",\"certStatus\":\"1\",\"morgName\":\"鼓楼区民政局\",\"regMon\":\"0.01\",\"morgArea\":\"056BA3C52BC645209958D0DECFD8113B\",\"legalPeople\":\"张瑞富\",\"checkResult\":\"\",\"regDate\":\"2014-09-22\",\"fetchPhone\":\"\",\"fetchPeople\":\"\",\"printTime\":\"\",\"actArea\":\"\",\"abbreviation\":\"\",\"ifCharity\":\"\",\"fetchDate\":\"\",\"organCode\":\"XXXXXXXXX\",\"receivePeople\":\"\",\"residence\":\"汉口路47号204室\",\"issueReason\":\"6\",\"javaClass\":\"Record\",\"issuePeople\":\"\",\"signEndDate\":\"2019-09-22\",\"ifBranch\":\"0\"}]}";
            Gson gson = new Gson();
            Result r = gson.fromJson(html, Result.class);
            if (r.getRows().size() == 1) {
                Orgnization org = r.getRows().get(0);
                if (org != null) {
                    //写入查询结果
                    excelUtil.writeCell(sheet, i, lastCellNum + 1, org.getResidence());
                    excelUtil.writeCell(sheet, i, lastCellNum + 2, org.getRegDate());
                    excelUtil.writeCell(sheet, i, lastCellNum + 3, org.getSignDate());
                    excelUtil.writeCell(sheet, i, lastCellNum + 4, org.getSignBeginDate());
                    excelUtil.writeCell(sheet, i, lastCellNum + 5, org.getSignEndDate());
                }
            }
        }

        //保存excel
        excelUtil.write(workbook);
    }

    private static String post(String url, String json) throws IOException {
        //生成post对象
        CloseableHttpClient client = HttpClients.createDefault();

        /*//设置代理IP、端口、协议（请分别替换）
        HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");
        //把代理设置到请求配置
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setProxy(proxy)
                .build();
        //实例化CloseableHttpClient对象
        CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();

        HttpHost proxy = new HttpHost("127.0.0.1", 8888);// 设置代理ip
        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
        CloseableHttpClient client = HttpClients.custom().setRoutePlanner(routePlanner).build();
*/
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("Cookie", "JSESSIONID=fwAAARroWlhmqNL8linn50SwrYp4Hq0q-csA; ava=0016");
        httpPost.addHeader("Host","59.201.8.162:6888");
        httpPost.addHeader("Connection","keep-alive");
        httpPost.addHeader("Origin","http://59.201.8.162:6888");
        httpPost.addHeader("X-Requested-With", "XMLHttpRequest");
        httpPost.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        httpPost.addHeader("Accept","*/*");
        httpPost.addHeader("Referer","http://59.201.8.162:6888/camsjs/jsp/cams/sorg/manage/ungov/somUngovCertList.jsp");
        httpPost.addHeader("Accept-Encoding"," gzip, deflate");
        httpPost.addHeader("Accept-Language","zh-CN,zh;q=0.9");

        //设置参数
        StringEntity se = new StringEntity(json);
        se.setContentType("text/json");
        httpPost.setEntity(se);
        //获取响应
        CloseableHttpResponse response = null;
        response = client.execute(httpPost);
        HttpEntity entity2 = null;
        entity2 = response.getEntity();
        String s2 = EntityUtils.toString(entity2, "UTF-8");
        System.out.println(s2);
        return s2;
    }

    private static int writeExcelBaidu(int pn, ExcelUtil excelUtil, XSSFSheet sheet, int offset) throws Exception {
        String url = "http://www.baidu.com/s?wd=word&pn=" + pn;
        HttpUtil httpUtil = new HttpUtil();
        String html = httpUtil.get(url);
        List<String> result = parse(html);
        for (int i = 0; i < result.size(); i++) {
            excelUtil.writeCell(sheet, offset, 0, String.valueOf(offset));
            excelUtil.writeCell(sheet, offset, 1, result.get(i));
            offset++;
        }
        return offset;
    }

    private static List<String> parse(String html) {
        List<String> result = new ArrayList<String>();
        Document doc = Jsoup.parse(html);
        Elements divContainer = doc.getElementsByClass("c-container");
        for (Element div : divContainer) {
            Element aTitle = div.getElementsByTag("a").get(0);
            result.add(aTitle.text());
        }
        return result;
    }

    private static void loadBaidu(ExcelUtil excelUtil, XSSFSheet sheet) throws Exception {
        //读取网页地址
        int pn = 0;
        int totalPage = 10;
        int offset = 0;
        excelUtil.writeCell(sheet, 0, 0, "id");
        excelUtil.writeCell(sheet, 0, 1, "title");
        offset++;
        for (int i = 0; i < totalPage; i++) {
            System.out.println("正在解析第" + (i + 1) + "个网页...");
            pn = i * 10;
            offset = writeExcelBaidu(pn, excelUtil, sheet, offset);
            System.out.println("第" + (i + 1) + "个网页解析成功");
        }
    }

    private static void checkFile() {
        /*//是否强行删除已存在的文件
        int del = 0;
        if (args.length > 0) {
            del = Integer.parseInt(args[0]);
        }
        //获取用户主目录的路径
        File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
        System.out.println(desktopDir);
        File excelFile = new File(desktopDir, "nicole.xlsx");
        if (excelFile.exists()) {
            if (del == 1) {
                excelFile.delete();
            } else {
                throw new FileAlreadyExistsException("文件已经存在！");
            }
        }*/
    }
}
