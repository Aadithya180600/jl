<!DOCTYPE html>
<html>
    <head>
        <title>Message</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <p>
            Image: ${image}
        </p>

        <#if time??>
            <p>Date and time: ${time}</p>
        </#if>

    </body>
</html>