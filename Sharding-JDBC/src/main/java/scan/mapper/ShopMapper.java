package scan.mapper;

import org.springframework.stereotype.Repository;
import scan.po.AdressPO;

import java.util.List;

@Repository
public interface ShopMapper {

    public void insert(List<AdressPO> orderPOList);


    public List<AdressPO> find(AdressPO orderPOList);
}
