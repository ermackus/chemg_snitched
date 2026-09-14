package com.alibaba.mtl.appmonitor.d;

import com.alibaba.mtl.appmonitor.model.Metric;
import com.alibaba.mtl.appmonitor.model.ConfigMetric;
import com.alibaba.mtl.appmonitor.model.Measure;
import com.alibaba.mtl.appmonitor.model.MeasureSet;
import org.json.JSONArray;
import com.alibaba.mtl.appmonitor.model.MetricRepo;
import com.alibaba.mtl.appmonitor.f.b;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

class h extends a<JSONObject>
{
    private String o;
    protected Map<String, i> r;
    
    public h(final String o, final int n) {
        super(n);
        this.o = o;
        this.r = (Map<String, i>)new HashMap();
    }
    
    public boolean a(final int n, final String s, final Map<String, String> map) {
        final Map<String, i> r = this.r;
        if (r != null) {
            final i i = (i)r.get((Object)s);
            if (i != null) {
                return i.a(n, map);
            }
        }
        return this.a(n);
    }
    
    public void b(final JSONObject jsonObject) {
        this.a(jsonObject);
        try {
            final JSONArray optJSONArray = jsonObject.optJSONArray("monitorPoints");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); ++i) {
                    final JSONObject jsonObject2 = optJSONArray.getJSONObject(i);
                    final String optString = jsonObject2.optString("monitorPoint");
                    final String optString2 = jsonObject2.optString("metric_comment_detail");
                    if (b.c(optString)) {
                        i j;
                        if ((j = (i)this.r.get((Object)optString)) == null) {
                            j = new i(optString, this.n);
                            this.r.put((Object)optString, (Object)j);
                        }
                        j.b(jsonObject2);
                        final Metric metric = MetricRepo.getRepo().getMetric(this.o, optString);
                        if (metric != null) {
                            metric.setCommitDetailFromConfig(optString2);
                        }
                        final Object opt = jsonObject2.opt("measures");
                        if (opt instanceof JSONArray) {
                            final JSONArray jsonArray = (JSONArray)opt;
                            final MeasureSet create = MeasureSet.create();
                            for (int length = jsonArray.length(), k = 0; k < length; ++k) {
                                final JSONObject jsonObject3 = jsonArray.getJSONObject(k);
                                if (jsonObject3 != null) {
                                    final String optString3 = jsonObject3.optString("name");
                                    final Double value = jsonObject3.optDouble("min");
                                    final Double value2 = jsonObject3.optDouble("max");
                                    if (optString3 != null && value != null && value2 != null) {
                                        create.addMeasure(new Measure(optString3, Double.valueOf(0.0), value, value2));
                                    }
                                }
                            }
                            final MetricRepo repo = MetricRepo.getRepo();
                            final StringBuilder sb = new StringBuilder();
                            sb.append("config_prefix");
                            sb.append(this.o);
                            final String string = sb.toString();
                            final StringBuilder sb2 = new StringBuilder();
                            sb2.append("config_prefix");
                            sb2.append(optString);
                            final Metric metric2 = repo.getMetric(string, sb2.toString());
                            if (metric2 != null) {
                                MetricRepo.getRepo().remove(metric2);
                            }
                            final StringBuilder sb3 = new StringBuilder();
                            sb3.append("config_prefix");
                            sb3.append(this.o);
                            final String string2 = sb3.toString();
                            final StringBuilder sb4 = new StringBuilder();
                            sb4.append("config_prefix");
                            sb4.append(optString);
                            MetricRepo.getRepo().add((Metric)new ConfigMetric(string2, sb4.toString(), create));
                        }
                    }
                }
            }
        }
        catch (final Exception ex) {}
    }
}
