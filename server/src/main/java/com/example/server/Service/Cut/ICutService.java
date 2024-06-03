package com.example.server.Service.Cut;

import com.example.server.Pojo.Cut;

public interface ICutService {

    public void save(Cut cut);

    public Cut getCut(String cut);
}
