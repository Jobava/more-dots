(ns dots.core
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(println "This text is printed from src/dots/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:width 800
                          :height 600
                          :rows 100
                          :cols 100
                          :sep 20
                          :radius 3
}))

(defn hello-world []
  [:div {:name "wrapper"}
  [:svg {:width (:width @app-state)
         :height (:height @app-state)}
    [:g
      (for [x (range (:rows @app-state))
            y (range (:cols @app-state))]
           [:circle
              {:cx (* (:sep @app-state) x)
              :cy (* (:sep @app-state) y)
              :r (:radius @app-state)
              ;;:stroke "black"
              :stroke-width 10}]
      )
    ]
  ]])

(reagent/render-component [hello-world]
                          (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
