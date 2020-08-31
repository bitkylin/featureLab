package cc.bitky.demo.featurelab.service.java.generic;

import lombok.SneakyThrows;

import java.lang.reflect.*;
import java.util.Arrays;

/**
 * @author liMingLiang
 * @link https://zhuanlan.zhihu.com/p/64584427
 * @date 2020/8/29
 */
public class BitKylinTypeDemo {

    @SneakyThrows
    public static void main(String[] args) {
        typeVariable();
        parameterizedType();
        genericArrayType();
        wildcardType();
        clz();
    }

    private static void typeVariable() throws NoSuchFieldException {
        System.out.println("\n---- typeVariable-----");
        //用反射的方式获取属性 public V v;
        Field v = BitKylinTypeTest.class.getField("v");
        //获取属性类型
        TypeVariable typeVariable = (TypeVariable) v.getGenericType();
        System.out.println("TypeVariable1:" + typeVariable);
        //获取类型变量上界
        System.out.println("TypeVariable2:" + Arrays.asList(typeVariable.getBounds()));
        //获取类型变量声明载体
        System.out.println("TypeVariable3:" + typeVariable.getGenericDeclaration());

        //1.8 AnnotatedType: 如果这个这个泛型参数类型的上界用注解标记了，我们可以通过它拿到相应的注解
        AnnotatedType[] annotatedTypes = typeVariable.getAnnotatedBounds();
        System.out.println("TypeVariable4:" + Arrays.asList(annotatedTypes) + " : " +
                Arrays.asList(annotatedTypes[0].getAnnotations()));
        System.out.println("TypeVariable5:" + typeVariable.getName());
    }

    private static void parameterizedType() throws NoSuchFieldException {
        System.out.println("\n---- parameterizedType-----");
        Field list = BitKylinTypeTest.class.getField("list");
        Type genericType1 = list.getGenericType();
        //参数类型1:java.util.List<T>
        System.out.println("参数类型1:" + genericType1.getTypeName());

        Field map = BitKylinTypeTest.class.getField("map");
        Type genericType2 = map.getGenericType();
        //参数类型2:java.util.Map<java.lang.String, T>
        System.out.println("参数类型2:" + genericType2.getTypeName());

        if (genericType2 instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) genericType2;
            Type[] types = pType.getActualTypeArguments();
            //参数类型列表:[class java.lang.String, T]
            System.out.println("参数类型列表:" + Arrays.asList(types));
            //参数原始类型:interface java.util.Map
            System.out.println("参数原始类型:" + pType.getRawType());
            //参数父类类型:null,因为Map没有外部类，所以为null
            System.out.println("参数父类类型:" + pType.getOwnerType());
        }
    }

    private static void genericArrayType() throws NoSuchFieldException {
        System.out.println("\n---- genericArrayType-----");
        Field tArray = BitKylinTypeTest.class.getField("tArray");
        System.out.println("数组参数类型1:" + tArray.getGenericType());
        Field ltArray = BitKylinTypeTest.class.getField("ltArray");
        //数组参数类型2:java.util.List<T>[]
        System.out.println("数组参数类型2:" + ltArray.getGenericType());
        if (tArray.getGenericType() instanceof GenericArrayType) {
            GenericArrayType arrayType = (GenericArrayType) tArray.getGenericType();
            //数组参数类型3:T
            System.out.println("数组参数类型3:" + arrayType.getGenericComponentType());
        }
    }

    private static void wildcardType() throws NoSuchFieldException {
        System.out.println("\n---- wildcardType-----");
        Field mapWithWildcard = BitKylinTypeTest.class.getField("mapWithWildcard");
        //先获取属性的泛型类型 Map<? super String, ? extends Number>
        Type wild = mapWithWildcard.getGenericType();
        if (wild instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType) wild;
            //获取<>里面的参数变量 ? super String, ? extends Number
            Type[] actualTypes = pType.getActualTypeArguments();
            System.out.println("WildcardType1:" + Arrays.asList(actualTypes));
            //? super java.lang.String
            WildcardType first = (WildcardType) actualTypes[0];
            //? extends java.lang.Number
            WildcardType second = (WildcardType) actualTypes[1];
            //WildcardType2: lower:[class java.lang.String]  upper:[class java.lang.Object]
            System.out.println("WildcardType2: lower:" + Arrays.asList(first.getLowerBounds()) + "  upper:" + Arrays.asList(first.getUpperBounds()));
            //WildcardType3: lower:[]  upper:[class java.lang.Number]
            System.out.println("WildcardType3: lower:" + Arrays.asList(second.getLowerBounds()) + "  upper:" + Arrays.asList(second.getUpperBounds()));
        }
    }

    private static void clz() throws NoSuchFieldException {
        System.out.println("\n---- class-----");
        Field tClass = BitKylinTypeTest.class.getField("testClass");
        System.out.println("Class1:" + tClass.getGenericType());//获取泛型类型，由于我们这个属性声明时候没有使用泛型，所以会获得原始类型
        Field tClass2 = BitKylinTypeTest.class.getField("testClass2");
        System.out.println("Class2:" + tClass2.getGenericType());//获取泛型类型
    }
}
