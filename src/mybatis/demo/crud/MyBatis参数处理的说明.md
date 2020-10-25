#关于MyBatis参数处理的说明
##一、单个参数（MyBatis不会做特殊处理）
`#`{参数名/任意名}：直接取出参数值
##二、多个参数（MyBatis会做特殊处理）
处理方式：多参数会被封装成一个Map
参数调用：
（1）key:(param1...paramN)/参数的索引
（2）value:传入的参数值
（3）#{从Map中获取指定的key值}
###（1）命名参数（明确指定封装参数时Map的key）
（1.1）注解：@Param("id")
（1.2）调用：#{id(指定的key)}
###（2）POJO作为参数
（2.1）如果多个参数正好是我们业务逻辑的数据模型，我们就可以直接传入POJO
（2.2）#{属性名}：取出传入POJO的属性值
###（3）Map作为参数
（3.1）如果多个参数不是业务模型中的数据，没有对应的POJO，不经常使用，为了方便我们可以传入Map
（3.2）#{Key}取出Map中对应的值
###（4）TO作为参数
（4.1）如果多个参数不是业务模型中的数据，但要经常使用，推荐编写一个TO（Transfer Object）<数据传输对象>
（4.2）Page{
            int index;
            int size;}
##三、关于MyBatis参数处理的一些思考(3种特殊情况)
图片路径：static/image/3种特殊情况思考.png
##四、#{}和${}的相同点和区别
（1）相同点：可以获取map中的值或者pojo对象属性的值
（2）不同点：
（2-1）#{}是以预编译的形式，将参数设置到SQL语句中（PreparedStatement）(可以防止SQL注入)
（2-2）${}取出的值直接拼接在SQL中（可能会带来安全问题）
      大多数情况下都应该使用#{}
      在原生JDBC不支持占位符的地方我们就可以使用${}来取值（分表、排序）
 （2-3）static/image/两种取值方式的区别.png
 ##五、`#`{}:更丰富的用法：
 规定参数的一些规则：
 　　javaType、 jdbcType、 mode（存储过程）、 numericScale、
 　　resultMap、 typeHandler、 jdbcTypeName、 expression（未来准备支持的功能）； 
 　　jdbcType通常需要在某种特定的条件下被设置：
     在我们数据为null的时候，有些数据库可能不能识别mybatis对null的默认处理。比如Oracle（报错）；
 　　JdbcType OTHER：无效的类型；因为mybatis对所有的null都映射的是原生Jdbc的OTHER类型，oracle不能正确处理;
 　　由于全局配置中：jdbcTypeForNull=OTHER；oracle不支持；两种办法
 　　1、#{email,jdbcType=OTHER};
 　　2、jdbcTypeForNull=NULL
 　　　　<setting name="jdbcTypeForNull" value="NULL"/>


