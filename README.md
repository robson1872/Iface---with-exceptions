# Iface with exceptions and refactoried
<h2>Code Smells</h2>
<h3>Duplicated Code</h3>
<p>Muitas funções da main apresentavam código duplicado, <b>IdBylogin</b> foi criada por exemplo pois o mesmo código estava em várias funções.</p>
<h3>Long Method</h3>
<p>Alguns métodos estavam muito grandes, assim dividimos parte dele em outros métodos.</p>
<h3>Generative Speculation</h3>
<p>Alguns métodos getters e setters não estavam sendo utilizados.</p>
<h3>Dead Code</h3>
<p>Alguns códigos que não faziam nada foram dispensados.</p>
<br>
<h2>Design Patterns</h2>
<h3>Bridge</h3>
<p>Alguns métodos da <b>Main</b> foram para novas classes, como <b>Menu</b></p>
<h3>Template Method</h3>
<p>Usada quando alteramos algo de <b>Interface<b> dentro da classe <b>Comunity</b></p>
<h3>Interpreter Pattern</h3>
<p>Criamos métodos para checar o tipo de um objeto, evitando repetição de código.</p>
