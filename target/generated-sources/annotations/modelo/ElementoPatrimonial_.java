package modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Detalle;
import modelo.Favorito;
import modelo.Tipo;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-02-24T18:14:44")
@StaticMetamodel(ElementoPatrimonial.class)
public class ElementoPatrimonial_ { 

    public static volatile SingularAttribute<ElementoPatrimonial, Tipo> tipo;
    public static volatile SingularAttribute<ElementoPatrimonial, String> horario;
    public static volatile ListAttribute<ElementoPatrimonial, Detalle> detalleList;
    public static volatile SingularAttribute<ElementoPatrimonial, String> direccion;
    public static volatile ListAttribute<ElementoPatrimonial, Favorito> favoritoList;
    public static volatile SingularAttribute<ElementoPatrimonial, String> localidad;
    public static volatile SingularAttribute<ElementoPatrimonial, Integer> id;
    public static volatile SingularAttribute<ElementoPatrimonial, String> nombre;

}