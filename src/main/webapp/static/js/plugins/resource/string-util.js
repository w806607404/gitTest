/**
 * 自定义方法<p>
 * 字符串截去首尾空白
 * @returns
 */
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g,"");
}
