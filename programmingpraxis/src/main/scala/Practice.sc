import scala.util.Try
import scala.util.Failure
import java.math.BigInteger

object Practice {
//val TRACING_LINE_REGEX = """.*href="(/user..etf_id=\d*).*(tracing_.*.zip).*ft_col_last_mod_date..(\d*-\d*-\d* \d*:\d*:\d*)..td.""".r
  val TRACING_LINE_REGEX = """.*href="(/user..etf_id=\d*).*(tracing_.*.(zip|7z)).*ft_col_last_mod_date..(\d*-\d*-\d* \d*:\d*:\d*)..td.""".r
                                                  //> TRACING_LINE_REGEX  : scala.util.matching.Regex = .*href="(/user..etf_id=\d*
                                                  //| ).*(tracing_.*.(zip|7z)).*ft_col_last_mod_date..(\d*-\d*-\d* \d*:\d*:\d*)..t
                                                  //| d.
  
  def text = """<td class="ft_col_entry_type"><img src="/lib/faenza-icon-theme_1.3/Faenza/mimetypes/16/application-x-7zip.png" class="ft_icon"></td>	<td class="ft_col_title"><a href="/user/?etf_id=558684" target="_blank">tracing_CLU22_MMX2P_AU_ER_G35_041PROD.7z</a><br><span class="path"><span class="pathname">Path:</span><a class="path_segment" href="/user/?etf_id=254837" onclick="return browse(254837);">MIB 2+</a><span class="pathslash">/</span><a class="path_segment" href="/user/?etf_id=255363" onclick="return browse(255363);">MIB2p Deliveries ESO</a><span class="pathslash">/</span><a class="path_segment" href="/user/?etf_id=558643" onclick="return browse(558643);">CLU22_MMX2P_AU_ER_G35_041</a></span></td>	<td class="ft_col_last_mod_date">2017-01-12 16:25:34</td>	<td class="ft_col_last_mod_by"><a href="mailto:eso.Group.nbsysint01@esolutions.de">Nightly SYSINT 01</a></td>	<td class="ft_col_download_count">16</td>	<td class="ft_col_file_size">465.15&nbsp;KB</td></tr>"""
                                                  //> text: => String
  
  TRACING_LINE_REGEX.findAllIn(text).matchData.map(_.subgroups).flatten.mkString("\r\n")
                                                  //> res0: String = /user/?etf_id=558684
                                                  //| tracing_CLU22_MMX2P_AU_ER_G35_041PROD.7z
                                                  //| 7z
                                                  //| 2017-01-12 16:25:34
}