# MyBatis数据操作工具类 jut_mybatis

mapper:
<pre><code>public interface CustomerMapper {
    Integer deleteCustomer(Long id);
}</code></pre>

调用示例:
<pre><code>Integer result = DbUtil.execute(new CallbackInterface<CustomerMapper,Integer>() {
    @Override
    public Integer execute(CustomerMapper mapper) {
        return mapper.deleteCustomer(id);
    }
},CustomerMapper.class);</code></pre>
