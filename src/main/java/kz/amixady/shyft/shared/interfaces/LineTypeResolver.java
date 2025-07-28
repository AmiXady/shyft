package kz.amixady.shyft.shared.interfaces;


import kz.amixady.shyft.shared.dto.Line;

public interface LineTypeResolver {
    //определяет тип строки
    public Line resolve(String line);
}
