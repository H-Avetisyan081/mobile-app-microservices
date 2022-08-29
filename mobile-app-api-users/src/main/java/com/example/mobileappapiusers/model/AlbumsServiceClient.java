package com.example.mobileappapiusers.model;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "albums", fallback = Albomfallback.class)
public interface AlbumsServiceClient {

    @GetMapping("/users/{id}/albums")
    List<AlbumResponseModel> getAlbums(@PathVariable String id);
}

@Component
class Albomfallback implements AlbumsServiceClient {

    @Override
    public List<AlbumResponseModel> getAlbums(String id) {
        return new ArrayList<>();
       }
}

