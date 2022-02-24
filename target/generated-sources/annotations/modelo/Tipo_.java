package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.ElementoPatrimonial;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-02-24T18:14:44")
@StaticMetamodel(Tipo.class)
public class Tipo_ { 

    public static volatile ListAttribute<Tipo, ElementoPatrimonial> elementoPatrimonialList;
    public static volatile SingularAttribute<Tipo, String> imagen;
    public static volatile SingularAttribute<Tipo, Integer> id;
    public static volatile SingularAttribute<Tipo, String> nombre;

}