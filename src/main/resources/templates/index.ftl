
<#include "base.ftl">

<#macro page_body>
    <#if root??>
        <div> <a href="/edit?root=${root}" role="button" class="btn btn-primary">add</a> </div>
    <#else>
        <div> <a href="/edit" role="button" class="btn btn-primary">add root</a> </div>
    </#if>
    <div class="container">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>type</th>
                <th>index</th>
                <th>content</th>
                <th></th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <#if documents??>
                <#list documents as doc>
                    <tr>
                        <td>${doc.id!}</td>
                        <td>${doc.name!}</td>
                        <td>${doc.type!}</td>
                        <td>${doc.index!}</td>
                        <td>${doc.content!}</td>
                        <td><a href="/enter?id=${doc.id}" role="button" class="btn-primary">enter</a></td>
                        <td><a href="/edit?id=${doc.id}" role="button" class="btn-primary">edit</a></td>
                        <td><a href="/attributes?id=${doc.id}" role="button" class="btn-primary">attribute</a></td>
                        <td><a href="/delete?id=${doc.id}" role="button" class="btn-danger">delete</a></td>
                    </tr>
                </#list>
            </#if>
            </tbody>
        </table>
    </div>
</#macro>

<@display_page/>