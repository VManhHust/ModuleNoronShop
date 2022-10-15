package com.example.upview;

import NoronShop.jooq.data.tables.pojos.Product;
import NoronShop.jooq.data.tables.pojos.Vendor;
import com.example.api.service.ProductService;
import com.example.commons.data.mapper.ProductMapper;
import com.example.commons.data.request.ProductRequest;
import com.example.commons.data.response.ProductResponse;
import com.example.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Service
@Slf4j
public class upView {
    @Autowired
    private ProductMapper productMapper;
    private KafkaTemplate<String, String> kafkaTemplate;
    private ProductRepository productRepository;

    public upView(KafkaTemplate<String, String> kafkaTemplate, ProductRepository productRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.productRepository = productRepository;
    }
    private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);
    @KafkaListener(id = "123", topics = "First_topic")
    public void receive(@Payload List<Object> messages,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        for (int i = 0; i < messages.size(); i++) {
            LOG.info("Received message='{}' with partition-offset='{}'", messages.get(i),
                    partitions.get(i) + "-" + offsets.get(i));
        }
        LOG.info("All batch messages received");

        Map<Object, Long> productCountMap =
                messages.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        for (Map.Entry<Object, Long> map : productCountMap.entrySet()) {
            Product product = productRepository.findById((Integer) map.getKey());
            product.setNumReview((int) (product.getNumReview() + map.getValue()));
            productRepository.save(product);
        }
        //convert object sang json
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(messages);
            System.out.println(json);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
