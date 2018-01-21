<#include "base.ftl">

<#macro page_body>
<div class="container">
    <div> <a href="/add" role="button" class="btn btn-primary">Add attribute</a></div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>type</th>
            <th>value</th>
            <th>value_int</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#if attributes??>
        <#list attributes as atr>
        <tr>
            <td>${atr.id!}</td>
            <td>${atr.name!}</td>
            <td>${atr.type!}</td>
            <td>${atr.value!}</td>
            <td>${atr.value_int!}</td>
            <td><a href="/edit?id=${atr.id}" role="button" class="btn-primary">edit</a></td>
            <td><a href="/delete?id=${atr.id}" role="button" class="btn-danger">delete</a></td>
        </tr>
        </#list>
    </#if>
    </tbody>
    </table>
</div>

</#macro>

<@display_page/>