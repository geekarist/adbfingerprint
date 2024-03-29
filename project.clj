(def user-home (System/getenv "HOME"))

(defproject adbfingerprint "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]]
  :plugins [[lein-bin "0.3.5"]]
  :bin {:name     "adbfingerprint"
        :bin-path "~/bin"}
  :repl-options {:init-ns adbfingerprint.core}
  :main adbfingerprint.core
  :profiles {:uberjar {:aot :all}
             :dev     {:plugins [[lein-shell "0.5.0"]]}}
  :user-home ~user-home
  :aliases {"native-build"   ["shell"
                              "native-image" "--report-unsupported-elements-at-runtime"
                              "--initialize-at-build-time"
                              "-jar" "./target/${:uberjar-name:-${:name}-${:version}-standalone.jar}"
                              "-H:Name=./target/${:name}"]
            "native-install" ["shell"
                              "cp" "./target/${:name}" "${:user-home}/bin/"]
            "native-all" ["do" "uberjar," "native-build," "native-install"]})
