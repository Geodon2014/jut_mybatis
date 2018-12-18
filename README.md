# MyBatis数据操作工具类 jut_mybatis
MyBatis数据操作的工具类，封装了获取SqlSession，提交事务或回滚事务的逻辑。<br />
mapper：
<pre><code>public interface CustomerMapper {
    Integer deleteCustomer(Long id);
}</code></pre>

调用示例：
<pre><code>Integer result = DbUtil.execute(new CallbackInterface<CustomerMapper,Integer>() {
    @Override
    public Integer execute(CustomerMapper mapper) {
        return mapper.deleteCustomer(id);
    }
},CustomerMapper.class);</code></pre>
