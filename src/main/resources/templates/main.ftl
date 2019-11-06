<#import "parts/common.ftl" as c>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/main" class="form-inline">
            <#--    <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by tag">
                <button type="submit" class="btn btn-primary ml-2">Search</button>-->
            </form>
        </div>
    </div>

    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Add new Message
    </a>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" action="/order">
                <div class="form-group">
                    <input type="text" class="form-control" name="text" placeholder="Введите сообщение" />
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="tag" placeholder="Тэг">
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file" id="customFile">
                        <label class="custom-file-label" for="customFile">Choose file</label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </div>
            </form>
        </div>
    </div>

    <div class="card-columns">
        <#--<#list messages as message>
            <div class="card my-3">
                <#if message.filename??>
&lt;#&ndash;                   filename <img src="/img/${message.filename}" class="card-img-top">&ndash;&gt;
                </#if>
                <div class="m-2">
                    <span>{message.text}</span>
                    <i>{message.tag}</i>
                </div>
                <div class="card-footer text-muted">
                    {message.authorName}
                </div>
            </div>
        <#else>
            No message
        </#list>-->
    </div>
</@c.page>


<#--
<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <div>
        <@l.logout />
        <span><a href="/user">User List</a></span>
    </div>
    <div>
       &lt;#&ndash; <form method="post">
            <input type="text" name="text" placeholder="Введите сообщение" />
            <input type="text" name="tag" placeholder="Тэг">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Добавить</button>
        </form>&ndash;&gt;
    </div>
    <div>Список МЕНЮ</div>
   &lt;#&ndash; <form method="get" action="/main">
        <input type="text" name="filter" >
        <button type="submit">Найти</button>
    </form>&ndash;&gt;

    <select multiple class="dropdown-menu">
                    <#list pizzas as pizza>
                        <input type="checkbox" value="${pizza.id}"> <option> ${pizza.name}</option></input>
                    </#list>
    </select>


&lt;#&ndash;
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="button-group">

                    <button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-cog"></span> <span class="caret"></span></button>
                    <#list pizzas as pizza>
                    <select multiple class="dropdown-menu">
                    <option class="small" data-value="${pizza.id}" tabIndex="-1"><input type="checkbox"/>${pizza.name}</a></option>
                            <li><a href="#" class="small" data-value="option1" tabIndex="-1"><input type="checkbox"/>&nbsp;Option 1</a></li>
                    </select>
                    </#list>

                </div>
            </div>
        </div>
    </div>
&ndash;&gt;


&lt;#&ndash;    <form action="buyPizza" method="post">
        <select multiple class="custom-select col-md-2" id="inputGroupSelect06">
            <#if pizzas??>
                <#list pizzas as pizza>
                    <option value="${pizza.id}">${pizza.name}</option>
                </#list>
            </#if>
        </select>
    </form>&ndash;&gt;


&lt;#&ndash; <#list pizzas as pizza>
        <div>
            <b>${pizza.id}</b>
            <i> <span>${pizza.name}</span></i>
            <strong>${pizza.price}</strong>
        </div>
    <#else>
        No message
    </#list>&ndash;&gt;
</@c.page>-->
