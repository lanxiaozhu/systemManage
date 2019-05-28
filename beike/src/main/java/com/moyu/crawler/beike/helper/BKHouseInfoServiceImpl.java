package com.moyu.crawler.beike.helper;

import cn.edu.hfut.dmic.webcollector.model.Page;
import com.moyu.crawler.beike.dao.mapper.MoyuHouseInfoMapper;
import com.moyu.crawler.beike.domian.MoyuHouseInfo;
import com.moyu.crawler.beike.util.BeanUtil;
import com.moyu.crawler.beike.util.IOUtil;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: wishm
 * @Date: 2019/5/8 15:48
 * @Description: 贝壳房屋信息解析
 */
@Service("bKHouseInfoService")
public class BKHouseInfoServiceImpl implements BKHouseInfoService {


    @Autowired
    MoyuHouseInfoMapper infoMapper;

    @Override
    public void getHouseDetail(Page page) {

        Elements select = page.select("ul[class=sellListContent]");

        Elements lis = select.select("li[class=clear]");
        for (Element li : lis) {
            MoyuHouseInfo houseInfo = new MoyuHouseInfo();

            Elements a = li.select("a[class=VIEWDATA CLICKDATA maidian-detail]");


            houseInfo.setHouseTitle(a.attr("title"));

            String sumPrice = li.select("div[class=totalPrice]").select("span").text();//价格
            houseInfo.setHouseSumPrice(Integer.parseInt(StringUtils.isNumeric(sumPrice) ? sumPrice : "0"));

            String location = li.select("div[class=positionInfo]").text();
            houseInfo.setHouseLocation(location);

            String price = li.select("div[class=unitPrice]").attr("data-price");
            houseInfo.setHousePrice(new BigDecimal(pattern(price)));

            String followInfo = li.select("div[class=followInfo]").text();
            String[] followSplit = followInfo.split("/");
            if (Objects.nonNull(followSplit) && followSplit.length == 2) {
                    houseInfo.setHouseStar(pattern(followSplit[0]));
                    houseInfo.setHousePublish(pattern(followSplit[1]));
            }
            houseInfo.setHouseTaxfree( li.select("div[class=taxfree]").text());

            houseInfo.setHouseSubway(StringUtils.isEmpty(li.select("span[class=subway]").text()) ? 1 : 0);

            houseInfo.setHouseLink(a.attr("href"));

            houseInfo.setHouseOrigin(0);
            //中楼层(共7层) | 1996年建 | 2室1厅 | 75.78平米 | 南
            String text = li.select("div[class=houseInfo]").text();
            String[] split = text.split("\\|");

            if (Objects.nonNull(split) && split.length==5) {
                    String substring = split[0].substring(0, 3);
                    houseInfo.setHouseFloorLevel(substring);

                    houseInfo.setHouseSumLevel(pattern(split[0]));

                    houseInfo.setHouseCreateTime(pattern(split[1]).toString());

                    houseInfo.setHouseLayout(split[2]);

                    houseInfo.setHouseMeasure(new BigDecimal(pattern(split[3])));

                    houseInfo.setHouseOrientation(split[4]);
            }else{
                houseInfo.setHouseExt(text);
            }

            String src = li.select("img[class=lj-lazy]").attr("data-original");
            try {
                houseInfo.setHouseFilePath(IOUtil.savePicture(src)+";"+src);
            } catch (IOException e) {
                e.printStackTrace();
            }


            if(houseInfo.getHouseSumPrice() != 0) infoMapper.insertSelective(houseInfo);

        }
    }

    /**
     * 正则 提取数字
     *
     * @param str
     * @return
     */
    public Integer pattern(String str) {
        String reg = "[^0-9]";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(str);
        String sumLevel = matcher.replaceAll("").trim();
        return Integer.parseInt(StringUtils.isNumeric(sumLevel) ? sumLevel : "0");
    }

}
