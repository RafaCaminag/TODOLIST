package br.com.rafaellacaminaga.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;



public class Utils {

    /* Ambos os metodos criados servem pra mesclar as informações nulas e as não nula,
     * agora, pra quê mesclar? como mesclar? quais infos estão sendo mescladas?
     * Não sei
     */




    // criando uma cópia da task armazenada no nosso repositório
    // vai copiar todas as properties não nulas
    // recebe de parâmetro um source e um target (de onde tá vindo e para onde vai)
    // static necessário pq aí nn precisamos instanciar a nossa classe
    public static void copyNonNullProperties(Object source, Object target) {


        // uma classe do próprio java que tem um metodo de copy properties
        // onde conseguimos copiar as propriedades de um objeto para um outro objeto
        // e como terceiro parametro passamos uma classe que agiria como "regra" que ele vai utilizar na copy
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));


    }
    // method que return um array de Strings
    // pega todos os nomes de todas as propriedades que estão retornando null
    // recebemos de parâmetro pra esse method um Object (bem generalista pq a ideia é reutilizar esse file
    // e method sempre que quisermos fazer um update parcial de um objeto)
    // static necessário pq aí nn precisamos instanciar a nossa classe
    public static String[] getNullPropertyNames(Object source) {

        // BeanWrapper eh uma classe do próprio java (serve pra quê ???)
        // eh uma interface que fornece uma forma de acessar as propriedades de um objeto dentro do java
        // pegamos um src e damos um new BeanWrapperImpl passando meu source
        // eh a implementação da interface acima
        final BeanWrapper src = new BeanWrapperImpl(source);


        // agr vamos obter as propriedades desse meu objeto
        // e vamos armazenar isso num array de PropertyDescriptor[] e vamos chamar de pds (???)
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        //criando um conj com as propriedades que eu tenho com valores nulos
        Set<String> emptyNames = new HashSet<>();

        // PropertyDescriptor de pd dentro dos pds
        // para cada propertyValue vamos pegar o getName e vamos obter o valor da propriedade atual
        // e assim teremos o src da sua propriedade atual da interação
        for(PropertyDescriptor pd: pds) {
            Object srcValue = src.getPropertyValue(pd.getName());

            // verificando se essa propriedade é nula
            if (srcValue == null) {

                // se a propriedade é nula, vamos adicionar o nome da propriedade no set emptyNames
                emptyNames.add(pd.getName());
            }

        }

        // agr com o set emptyNames vamos criar um array de Strings com os nomes das propriedades nulas
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);


    }


}
