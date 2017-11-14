package com.epsi.mediatheque.SOAP;


import music.GetMusicRequest;
import music.GetMusicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class MusicEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private MusicRepository musicRepository;

    @Autowired
    public MusicEndpoint(MusicRepository musicRepository){
        this.musicRepository = musicRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI,localPart = "getMusicRequest")
    @ResponsePayload
    public GetMusicResponse getMusic(@RequestPayload GetMusicRequest request){
        GetMusicResponse response = new GetMusicResponse();
        response.setMusic(musicRepository.findMusic(request.getName()));
        return response;
    }
}
