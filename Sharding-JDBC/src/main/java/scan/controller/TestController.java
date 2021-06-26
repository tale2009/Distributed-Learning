package scan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import scan.mapper.ShopMapper;
import scan.po.AdressPO;
import scan.util.IDWorker;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
public class TestController {
    @Autowired
    private ShopMapper shopMapper;;
    @RequestMapping("insert")
    public void insert() throws UnknownHostException {

        List<AdressPO> orderPOList=new ArrayList<AdressPO>();
        Date date=new Date();
        Random r = new Random();
        AdressPO orderPO=new AdressPO();
        orderPO.setAddressId(IDWorker.getInstance("eshop_address").nextId());
        orderPO.setUserId(orderPO.getAddressId());
        orderPO.setCreateUserId(orderPO.getAddressId());
        orderPO.setModifyUserId(orderPO.getAddressId());
        orderPO.setProvinceCode(String.valueOf(r.nextInt(33)+1));
        orderPO.setCityCode(Long.valueOf(String.valueOf(r.nextInt(49)+1)));
        orderPO.setRegionCode(String.valueOf(r.nextInt(89)+1));
        orderPO.setCreateTime(date);
        orderPO.setDetail("使用读写分离");
        orderPO.setAddressName("");
        orderPO.setModifyTime(date);
        orderPO.setCountryCode("");
        orderPO.setPhone("");
        orderPOList.add(orderPO);
        shopMapper.insert(orderPOList);
    }
    @RequestMapping("find")
    public List<AdressPO> find(Long citycode)
    {
        AdressPO adressPO = new AdressPO();
        adressPO.setCityCode(citycode);
        return shopMapper.find(adressPO);
    }
}
