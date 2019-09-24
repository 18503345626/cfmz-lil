package com.baizhi.service.impl;

import com.baizhi.dao.BannerDAO;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Resource
    private BannerDAO bannerDAO;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Map<String, Object> showAll(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        List<Banner> banners = bannerDAO.selectAll(page, rows);
        Integer count = bannerDAO.selectCount();
        map.put("rows",banners);
        map.put("records",count);
        int total=count%rows==0?count/rows:count/rows+1;
        map.put("total",total);
        map.put("page",page);
        return map;
    }

    @Override
    public String addOne(Banner banner) {
        banner.setUp_date(new Date());
        banner.setId(UUID.randomUUID().toString());
        banner.setStatus("冻结");
        bannerDAO.insertOne(banner);
        return banner.getId();
    }

    @Override
    public String updateOne(Banner banner) {
        bannerDAO.updateOne(banner);
        if(banner.getImg_path().equals("")||banner.getImg_path()==null){
            return null;
        }else {
            return banner.getId();
        }
    }

    @Override
    public void deleteOne(String id) {
        bannerDAO.deleteById(id);
    }

    @Override
    public void changeStatus(String rowId) {
        System.out.println(rowId+"=============");
       Banner banner= bannerDAO.selectById(rowId);
        if(banner.getStatus().equals("冻结")){
            banner.setStatus("已激活");
        } else{
            banner.setStatus("冻结");
        }
        bannerDAO.updateOne(banner);
    }

    @Override
    public List<Banner> showBanners() {
        List<Banner> banners = bannerDAO.selectAllBanner();
        List<Banner> list = new ArrayList<>();
        for (Banner banner : banners) {
           if(!banner.getStatus().equals("冻结")){
               list.add(banner);
           }
        }
        List<Banner> bannerss = new ArrayList<>();
        if(list.size()!=0){
            if(list.size()<=6){
                return list;
            }else{
                Random random = new Random();
                Set<Banner> set = new HashSet<>();
                while (true){
                    set.add(list.get(random.nextInt(list.size())));
                    if(set.size()==6)break;
                }
                for (Banner banner : set) {
                    bannerss.add(banner);
                }
            }
        }
        return bannerss;
    }
}
