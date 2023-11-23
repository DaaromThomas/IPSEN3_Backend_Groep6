package com.hsleiden.vdlelie;

import com.hsleiden.vdlelie.dao.PackagingRepository;
import com.hsleiden.vdlelie.model.Packaging;
import com.hsleiden.vdlelie.services.PackagingService;
import com.hsleiden.vdlelie.services.PackagingServiceImpl;
import com.hsleiden.vdlelie.model.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class GetPackagingTest {

    @Mock
    private PackagingRepository packagingRepository;

    @Mock
    private Stock stock;

    private PackagingService SUT;

    @BeforeEach
    public void setup() {
        this.SUT = new PackagingServiceImpl(packagingRepository);
    }

    @Test
    public void should_return_all_packaging(){
        Packaging packaging1 = new Packaging();
        packaging1.setId("id1");
        packaging1.setStock(stock);
        packaging1.setAmountinstock(10);
        packaging1.setMinAmount(5);
        packaging1.setName("name1");
        packaging1.setPackagingGroup("group1");

        Packaging packaging2 = new Packaging();
        packaging2.setId("id2");
        packaging2.setStock(stock);
        packaging2.setAmountinstock(20);
        packaging2.setMinAmount(10);
        packaging2.setName("name2");
        packaging2.setPackagingGroup("group2");

        List<Packaging> dummyPackaging = Arrays.asList(packaging1, packaging2);

        when(this.packagingRepository.findAll()).thenReturn(dummyPackaging);
        List<Packaging> actualPackaging = SUT.findAll();
        assertThat(actualPackaging.size(), is(dummyPackaging.size()));
    }

    @Test
    public void should_return_the_right_packaging_by_id(){
        Packaging dummyPackaging = new Packaging();
        dummyPackaging.setId("id1");
        dummyPackaging.setStock(stock);
        dummyPackaging.setAmountinstock(10);
        dummyPackaging.setMinAmount(5);
        dummyPackaging.setName("name1");
        dummyPackaging.setPackagingGroup("group1");

        String id = dummyPackaging.getId();

        when(packagingRepository.findById(id)).thenReturn(Optional.of(dummyPackaging));

        Optional<Packaging> actualPackaging = SUT.findById(id);

        assertThat(actualPackaging.get().getName(), is(dummyPackaging.getName()));
    }
}
